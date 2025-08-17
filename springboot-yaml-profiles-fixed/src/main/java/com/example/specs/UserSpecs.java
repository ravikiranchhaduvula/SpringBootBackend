package com.example.specs;

import com.example.entity.User;
import com.example.entity.UserRole;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public final class UserSpecs {
    private UserSpecs() {}

    public static Specification<User> nameContains(String q) {
        return (root, cq, cb) -> (q == null || q.isBlank())
                ? cb.conjunction()
                : cb.like(cb.lower(root.get("name")), "%" + q.toLowerCase() + "%");
    }

    public static Specification<User> emailDomain(String domain) {
        return (root, cq, cb) -> (domain == null || domain.isBlank())
                ? cb.conjunction()
                : cb.like(cb.lower(root.get("email")), "%@" + domain.toLowerCase());
    }

    /**
     * NOTE: This requires a field named 'createdAt' on User.
     * If your entity does not have it, rename the path here to whatever you use (e.g. 'createdOn')
     * or remove this spec.
     */
    public static Specification<User> createdBetween(Instant from, Instant to) {
        return (root, cq, cb) -> {
            if (from == null && to == null) return cb.conjunction();
            if (from != null && to != null) return cb.between(root.get("createdAt"), from, to);
            return (from != null)
                    ? cb.greaterThanOrEqualTo(root.get("createdAt"), from)
                    : cb.lessThanOrEqualTo(root.get("createdAt"), to);
        };
    }

    /** Users having a role by its name (matches your UserRole.roleName). */
    public static Specification<User> hasRole(String roleName) {
        return (root, cq, cb) -> {
            if (roleName == null || roleName.isBlank()) return cb.conjunction();
            Join<User, UserRole> ur = root.join("userRoles", JoinType.LEFT); // <-- was "roles"
            // If you use @EmbeddedId with id.roleName, use: ur.get("id").get("roleName")
            return cb.equal(cb.lower(ur.get("roleName")), roleName.toLowerCase());
        };
    }

    /** Soft-delete: DB uses deleted_at. Keep only rows where deletedAt is NULL. */
    public static Specification<User> notDeleted() {
        return (root, cq, cb) -> cb.isNull(root.get("deletedAt")); // <-- was 'deleted' boolean
    }

    /**
     * Optional helper: fetch userRoles to avoid N+1 (only on main select, not on count queries).
     * Use this by composing: whereSpec.and(UserSpecs.fetchUserRoles())
     */
    public static Specification<User> fetchUserRoles() {
        return (root, query, cb) -> {
            if (User.class.equals(query.getResultType())) {
                root.fetch("userRoles", JoinType.LEFT);
                query.distinct(true);
            }
            return cb.conjunction();
        };
    }

    /** Example composition helper */
    public static Specification<User> search(String q, String domain, String roleName) {
        return Specification.where(notDeleted())
                .and(nameContains(q).or(emailDomain(q))) // free-text on name/email
                .and(emailDomain(domain))
                .and(hasRole(roleName));
    }
}
