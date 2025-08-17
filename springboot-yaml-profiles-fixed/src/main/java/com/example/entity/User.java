package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
@NamedNativeQuery(
        name = "User.findByEmailNativeEntity",
        query = "SELECT * FROM users WHERE email = :email",
        resultClass = User.class
)
@SqlResultSetMapping(
        name = "UserDTOResult",
        classes = @ConstructorResult(
                targetClass = com.example.DTO.UserDTO.class,
                columns = {
                        @ColumnResult(name = "id",    type = Long.class),
                        @ColumnResult(name = "name",  type = String.class),
                        @ColumnResult(name = "email", type = String.class)
                }
        )
)
@NamedNativeQuery(
        name = "User.findAllDTOs",
        query = "SELECT id, name, email FROM users ORDER BY id DESC",
        resultSetMapping = "UserDTOResult"
)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    // in User.java
    @Column(nullable = false)        // keep this
    private boolean deleted = false; // and this default

    private Instant deletedAt;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    // One user → many UserRole rows
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<UserRole> userRoles = new HashSet<>();

    // ✅ Lightweight constructor for native queries or projections
    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.deleted = false;
        this.deletedAt = null;
        this.userRoles = new HashSet<>();
    }
}
