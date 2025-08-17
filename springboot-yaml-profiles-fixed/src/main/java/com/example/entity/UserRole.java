package com.example.entity;

// com.example.entity.UserRole
import com.example.entity.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_roles")
@IdClass(UserRoleId.class)
@Data @NoArgsConstructor @AllArgsConstructor
public class UserRole {

    @Id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;               // name "user" matches IdClass field

    @Id
    @Column(name = "role_name", nullable = false, length = 64)
    private String roleName;

    @Column(name = "description")
    private String description;
}