package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserCacheServiceL1 {
    private final UserRepository repo;

    public UserCacheServiceL1(UserRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public String demoL1(long id) {
        System.out.println("➡️ First load (should hit DB)");
        User u1 = repo.findById(id).orElse(null);

        System.out.println("➡️ Second load in SAME TX (should be from L1, no SQL)");
        User u2 = repo.findById(id).orElse(null);

        return "L1 cache: u1 == u2 ? " + (u1 == u2);
    }
}
