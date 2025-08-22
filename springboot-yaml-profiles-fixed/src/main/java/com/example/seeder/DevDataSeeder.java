package com.example.seeder;

import com.example.entity.User;
import com.example.entity.UserRole;
import com.example.repository.UserRepository;
import com.example.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class DevDataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final UserRoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public void run(String... args) {
        System.out.println("[SEED] DevDataSeeder running…");

        User admin = userRepository.findByEmail("admin@example.com")
                .orElseGet(() -> userRepository.save(
                        User.builder()
                                .name("Admin")
                                .email("admin@example.com")
                                .password(encoder.encode("secret")) // bcrypt (with {bcrypt} prefix via delegating)
                                .enabled(true)
                                .build()
                ));

        // repair password if needed
        if (!encoder.matches("secret", admin.getPassword())) {
            admin.setPassword(encoder.encode("secret"));
            admin.setEnabled(true);
            userRepository.save(admin);
            System.out.println("[SEED] Rehashed admin password");
        }

        // ensure ADMIN role exists (no lazy collection access)
        if (!roleRepository.existsByUser_IdAndRoleName(admin.getId(), "ADMIN")) {
            roleRepository.save(new UserRole(admin, "ADMIN", "seeded"));
        }
    }
}
