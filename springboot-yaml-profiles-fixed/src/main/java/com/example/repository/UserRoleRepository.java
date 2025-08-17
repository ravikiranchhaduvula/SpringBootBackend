package com.example.repository;

import com.example.entity.UserRole;
import com.example.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}
