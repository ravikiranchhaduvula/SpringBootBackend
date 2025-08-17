package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserCacheServiceL2_A {
    private final UserRepository repo;

    public UserCacheServiceL2_A(UserRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public User loadOnce(long id) {
        System.out.println("TX-A: Load user (expect SQL the first time)");
        return repo.findById(id).orElse(null);
    }
}
