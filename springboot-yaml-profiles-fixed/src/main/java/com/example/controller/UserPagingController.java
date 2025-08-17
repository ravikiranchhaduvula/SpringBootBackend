package com.example.controller;

import com.example.DTO.PagedResponse;
import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/paging")
public class UserPagingController {

    private final UserRepository repo;

    public UserPagingController(UserRepository repo) {
        this.repo = repo;
    }

    // GET /api/users?page=0&size=5&sort=name,asc
    @GetMapping
    public Page<User> list(
            @PageableDefault(page = 0, size = 5, sort = "id") Pageable pageable) {
        return repo.findAll(pageable);
    }

    @GetMapping("/paged")
    public PagedResponse<User> listPaged(
            @PageableDefault(page = 0, size = 5, sort = "id") Pageable pageable) {

        var page = repo.findAll(pageable);
        return new PagedResponse<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast()
        );
    }

    @GetMapping("/sorted")
    public Page<User> listSorted(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "asc") String dir) {

        Sort sort = (sortBy == null || sortBy.isBlank())
                ? Sort.by("id").ascending()
                : ("desc".equalsIgnoreCase(dir) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending());

        return repo.findAll(PageRequest.of(page, size, sort));
    }
}

