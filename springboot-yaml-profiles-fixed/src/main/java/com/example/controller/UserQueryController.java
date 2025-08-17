package com.example.controller;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/query")
public class UserQueryController {

    private final UserRepository repo;

    public UserQueryController(UserRepository repo) {
        this.repo = repo;
    }

    // GET /api/users/query/by-name?name=Ravi
    @GetMapping("/by-name")
    public List<User> byExactName(@RequestParam String name) {
        return repo.findUserByExactName(name);
    }

    // GET /api/users/query/search?q=ra&page=0&size=5
    @GetMapping("/search")
    public Page<User> search(@RequestParam String q,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "5") int size) {
        return repo.searchByNameLike(q, PageRequest.of(page, size));
    }

    // GET /api/users/query/native?name=Ravi
    @GetMapping("/native")
    public List<User> nativeByName(@RequestParam String name) {
        return repo.findNativeByName(name);
    }
}

