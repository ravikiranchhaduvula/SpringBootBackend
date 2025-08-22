package com.example.entity;

import java.io.Serializable;
import java.util.Objects;

public class UserRoleId implements Serializable {
    private Long user;        // must match field name in UserRole
    private String roleName;  // must match field name in UserRole

    public UserRoleId() {}    // required

    public UserRoleId(Long user, String roleName) {
        this.user = user;
        this.roleName = roleName;
    }

    // getters/setters (or make fields public)
    public Long getUser() { return user; }
    public void setUser(Long user) { this.user = user; }
    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRoleId that)) return false;
        return Objects.equals(user, that.user) &&
                Objects.equals(roleName, that.roleName);
    }
    @Override public int hashCode() { return Objects.hash(user, roleName); }
}
