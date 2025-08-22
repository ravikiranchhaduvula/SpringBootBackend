package com.example.service;

import com.example.entity.RefreshToken;
import com.example.repository.RefreshTokenRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository repo;
    private final UserRepository userRepo;

    // default 7 days; override in application.yml:
    // security:
    //   jwt:
    //     refresh-expiration-ms: 604800000
    @Value("${security.jwt.refresh-expiration-ms:604800000}")
    private long refreshTokenDurationMs;

    public RefreshTokenService(RefreshTokenRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    /** Create and persist a new refresh token for the given user id */
    @Transactional
    public RefreshToken create(Long userId) {
        var user = userRepo.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "user_not_found"));

        var rt = new RefreshToken();
        rt.setUser(user);
        rt.setToken(UUID.randomUUID().toString());
        rt.setExpiresAt(Instant.now().plusMillis(refreshTokenDurationMs));
        rt.setRevoked(false);
        return repo.save(rt);
    }

    /** Lookup by raw token string */
    public Optional<RefreshToken> findByToken(String token) {
        if (token == null || token.isBlank()) return Optional.empty();
        return repo.findByToken(token);
    }

    /** Ensure token is not expired/revoked; throw 401 if invalid */
    public void verifyNotExpiredOrRevoked(RefreshToken token) {
        if (token.isRevoked()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "refresh_revoked");
        }
        if (token.getExpiresAt().isBefore(Instant.now())) {
            // Optional: delete expired tokens to keep table tidy
            repo.delete(token);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "refresh_expired");
        }
    }

    /** Revoke by token string (returns true if changed) */
    @Transactional
    public boolean revoke(String token) {
        var opt = repo.findByToken(token);
        if (opt.isEmpty()) return false;
        var rt = opt.get();
        if (!rt.isRevoked()) {
            rt.setRevoked(true);
            repo.save(rt);
            return true;
        }
        return false;
    }

    /** Optional helper if you later want to rotate refresh tokens */
    @Transactional
    public RefreshToken rotate(RefreshToken oldToken) {
        verifyNotExpiredOrRevoked(oldToken);
        oldToken.setRevoked(true);
        repo.save(oldToken);
        return create(oldToken.getUser().getId());
    }
}
