package com.example.entity;

// com.example.entity.UserRole
import com.example.entity.User;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "user_roles")
@IdClass(UserRoleId.class)
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
public class UserRole {

    @Id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @Column(name = "role_name", nullable = false, length = 64)
    private String roleName;

    @Column(name = "description")
    private String description;

    /**
     * Equals/HashCode MUST NOT traverse associations.
     * Use the foreign key (user.id) + roleName instead.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRole other)) return false;
        Long thisUserId = this.user != null ? this.user.getId() : null;
        Long thatUserId = other.user != null ? other.user.getId() : null;
        return Objects.equals(thisUserId, thatUserId) &&
                Objects.equals(this.roleName, other.roleName);
    }

    @Override
    public int hashCode() {
        Long userId = this.user != null ? this.user.getId() : null;
        return Objects.hash(userId, roleName);
    }
}