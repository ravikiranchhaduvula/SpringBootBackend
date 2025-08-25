package com.example.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name must be ≤ 100 chars")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Email is invalid")
        @Size(max = 255, message = "Email must be ≤ 255 chars")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be ≥ 6 chars")
        String password


) {}

