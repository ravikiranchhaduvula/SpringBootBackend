package com.example.controller;

import com.example.DTO.CreateUserRequest;
import com.example.DTO.UserDTO;
import com.example.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Validated // enables @Min/@Pattern on params
public class UserApiController {

    private final UserService userService;
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    // GET /api/users/{id} -> might throw ResourceNotFoundException
    @GetMapping("/{id}")
    public UserDTO get(@PathVariable @Min(value = 1, message = "id must be ≥ 1") Long id) {
        return userService.getById(id);
    }

    // POST /api/users -> @Valid triggers MethodArgumentNotValidException on bad input
    @PostMapping
    public UserDTO create(@RequestBody @Valid CreateUserRequest req) {
        return userService.create(req);
    }
}

