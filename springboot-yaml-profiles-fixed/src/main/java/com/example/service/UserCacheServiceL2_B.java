package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserCacheServiceL2_B {
    private final UserRepository repo;

    public UserCacheServiceL2_B(UserRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public User loadAgain(long id) {
        System.out.println("TX-B: Load user (should hit L2, no SQL if cached)");
        return repo.findById(id).orElse(null);
    }
}
