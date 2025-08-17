package com.example.controller;

import com.example.entity.UserRole;
import com.example.repository.UserRoleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {

    private final UserRoleRepository repository;

    public UserRoleController(UserRoleRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public UserRole addRole(@RequestBody UserRole userRole) {
        return repository.save(userRole);
    }

    @GetMapping
    public List<UserRole> getRoles() {
        return repository.findAll();
    }
}
