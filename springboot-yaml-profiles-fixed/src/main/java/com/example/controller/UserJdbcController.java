package com.example.controller;

import com.example.entity.User;
import com.example.jdbc.UserJdbcRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users/jdbc")
public class UserJdbcController {

    private final UserJdbcRepository repo;

    public UserJdbcController(UserJdbcRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<User> list(@RequestParam(defaultValue = "10") int limit,
                           @RequestParam(defaultValue = "0") int offset) {
        return repo.findAll(limit, offset);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody User u) {
        long id = repo.create(u);
        return ResponseEntity.created(URI.create("/api/users/jdbc/" + id)).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody User u) {
        u.setId(id);
        int rows = repo.update(u);
        return rows == 0 ? ResponseEntity.notFound().build() : ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        int rows = repo.delete(id);
        return rows == 0 ? ResponseEntity.notFound().build() : ResponseEntity.noContent().build();
    }
}
