package com.example.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @Schema(example = "Alice")
        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name must be ≤ 100 chars")
        String name,

        @Schema(example = "alice@example.com")
        @NotBlank(message = "Email is required")
        @Email(message = "Email is invalid")
        @Size(max = 255, message = "Email must be ≤ 255 chars")
        String email,

        @Schema(example = "secret123", description = "Raw password")
        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be ≥ 6 chars")
        String password


) {}

