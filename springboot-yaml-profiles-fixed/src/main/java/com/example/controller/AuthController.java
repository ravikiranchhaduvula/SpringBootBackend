package com.example.controller;

import com.example.repository.UserRepository;
import com.example.security.jwt.JwtService;
import com.example.service.RefreshTokenService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepo;
    private final UserDetailsService userDetailsService; // <-- added

    /** LOGIN: returns access + refresh */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));

        var principal = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        var roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        // subject is your email/username
        String accessToken = jwtService.generate(principal.getUsername(), Map.of("roles", roles));

        // issue refresh token for this user
        var user = userRepo.findByEmail(principal.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "user_not_found"));
        var refreshToken = refreshTokenService.create(user.getId()); // returns entity or DTO with .getToken()

        return Map.of(
                "accessToken", accessToken,
                "tokenType", "Bearer",
                "refreshToken", refreshToken.getToken()
        );
    }

    /** REFRESH: returns a new access token (keeps or rotates refresh token, see service impl) */
    /*@PostMapping("/refresh")
    public Map<String, Object> refresh(@RequestBody RefreshRequest req) {
        var stored = refreshTokenService.findByToken(req.getRefreshToken())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid_refresh_token"));

        refreshTokenService.verifyNotExpiredOrRevoked(stored); // throws 401/403 on failure

        // Option A: keep same refresh token (simple)
        // Option B (recommended): rotate (revoke old, issue new) — expose new value if you implement rotation

        // Rebuild authorities to embed roles claim
        var ud = userDetailsService.loadUserByUsername(stored.getUser().getEmail());
        var roles = ud.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        String newAccess = jwtService.generate(ud.getUsername(), Map.of("roles", roles));

        return Map.of(
                "accessToken", newAccess,
                "tokenType", "Bearer",
                "refreshToken", stored.getToken() // or new rotated token if you implement rotation
        );
    }*/

    /** LOGOUT: revoke refresh token */
    @PostMapping("/logout")
    public Map<String, Object> logout(@RequestBody RefreshRequest req) {
        boolean ok = refreshTokenService.revoke(req.getRefreshToken());
        if (!ok) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid_refresh_token");
        return Map.of("status", "logged_out");
    }

    @Data
    public static class LoginRequest {
        private String username; // email in your app
        private String password;
    }

    @Data
    public static class RefreshRequest {
        private String refreshToken;
    }

    @PostMapping("/refresh")
    public Map<String, Object> refresh(@RequestBody RefreshRequest req) {
        // 1) Lookup stored token
        var stored = refreshTokenService.findByToken(req.getRefreshToken())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid_refresh_token"));

        // 2) Validate not expired/revoked (throws 401 if bad)
        refreshTokenService.verifyNotExpiredOrRevoked(stored);

        // 3) ROTATE: revoke old, create new (one-time use semantics)
        var newRefresh = refreshTokenService.rotate(stored); // old is now revoked

        // 4) Rebuild roles claim for the new access token
        var ud = userDetailsService.loadUserByUsername(newRefresh.getUser().getEmail());
        var roles = ud.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        // 5) Issue new access token
        String newAccess = jwtService.generate(ud.getUsername(), Map.of("roles", roles));

        // 6) Return BOTH the new access token and the new refresh token
        return Map.of(
                "accessToken", newAccess,
                "tokenType", "Bearer",
                "refreshToken", newRefresh.getToken()
        );
    }

}
