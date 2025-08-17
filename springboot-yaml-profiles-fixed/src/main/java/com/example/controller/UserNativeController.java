// src/main/java/com/example/controller/UserNativeController.java
package com.example.controller;

import com.example.DTO.UserDTO;
import com.example.entity.User;
import com.example.repository.UserNativeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/native")
public class UserNativeController {

    private final UserNativeRepository repo;

    public UserNativeController(UserNativeRepository repo) {
        this.repo = repo;
    }

    // GET /api/users/native/by-email?email=you@mail.com
    @GetMapping("/by-email")
    public List<User> byEmail(@RequestParam String email) {
        return repo.findByEmailEntity(email);
    }

    // GET /api/users/native/dtos
    @GetMapping("/dtos")
    public List<UserDTO> allAsDtos() {
        return repo.findAllAsDTOs();
    }
}

