package com.example.controller;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.specs.UserSpecs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

//Controller (entities returned, roles pre-fetched)
@RestController
@RequestMapping("/api/users/spec")
public class UserSpecController {

    private final UserRepository repo;
    public UserSpecController(UserRepository repo) { this.repo = repo; }

    // GET /api/users/spec?q=ra&domain=gmail.com
    @GetMapping("/search")
    public List<User> find(@RequestParam(required = false) String q,
                           @RequestParam(required = false) String domain) {
        var spec = UserSpecs.nameContains(q).and(UserSpecs.emailDomain(domain));
        return repo.findAll(spec); // roles loaded via @EntityGraph -> no N+1
    }

    // GET /api/users/spec?q=ra&domain=gmail.com&role=ADMIN&page=0&size=5&sort=name,asc
    @GetMapping
    public Page<User> search(@RequestParam(required = false) String q,
                             @RequestParam(required = false) String domain,
                             @RequestParam(required = false) String role,
                             @RequestParam(required = false) Instant from,
                             @RequestParam(required = false) Instant to,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "5") int size,
                             @RequestParam(defaultValue = "id,asc") String sort) {

        var parts = sort.split(",");
        var sortObj = (parts.length == 2 && "desc".equalsIgnoreCase(parts[1]))
                ? Sort.by(parts[0]).descending()
                : Sort.by(parts[0]).ascending();

        Specification<User> spec = Specification.where(UserSpecs.notDeleted())
                .and(UserSpecs.nameContains(q))
                .and(UserSpecs.emailDomain(domain))
                .and(UserSpecs.hasRole(role))
                .and(UserSpecs.createdBetween(from, to));

        return repo.findAll(spec, PageRequest.of(page, size, sortObj));
    }
}

