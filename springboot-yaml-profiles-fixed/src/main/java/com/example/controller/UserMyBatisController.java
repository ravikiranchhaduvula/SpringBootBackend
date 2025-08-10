package com.example.controller;

import com.example.entity.User;
import com.example.service.UserServiceMyBatis;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mybatis/users")
public class UserMyBatisController {
    private final UserServiceMyBatis userService;

    public UserMyBatisController(UserServiceMyBatis userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() { return userService.getAllUsers(); }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) { return userService.getUser(id); }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

