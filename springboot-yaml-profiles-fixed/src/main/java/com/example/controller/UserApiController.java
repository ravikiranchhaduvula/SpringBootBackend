package com.example.controller;

import com.example.DTO.CreateUserRequest;
import com.example.DTO.UserDTO;
import com.example.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users", description = "User management endpoints")
@RestController
@RequestMapping("/api/v1/users")
@Validated // enables @Min/@Pattern on params
public class UserApiController {

    private final UserService userService;
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Get user by id",
            description = "Returns a user if found, else 404",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "User found",
                            content = @Content(schema = @Schema(implementation = UserDTO.class))),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }
    )
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

