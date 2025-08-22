package com.example.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

// Recommended for business rules
@Service
public class UserAdminService {

    @PreAuthorize("hasRole('ADMIN')")
    public void hardDeleteUser(Long id) {
        // dangerous operation
    }
}