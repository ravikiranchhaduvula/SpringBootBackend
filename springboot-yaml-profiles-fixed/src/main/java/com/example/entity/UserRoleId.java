package com.example.entity;

// UserRoleId.java
import java.io.Serializable;
import java.util.Objects;

public class UserRoleId implements Serializable {
    // NAME MUST MATCH the entity's @Id property name: "user"
    private Long user;       // <— not userId
    private String roleName;

    public UserRoleId() {}
    public UserRoleId(Long user, String roleName) {
        this.user = user;
        this.roleName = roleName;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRoleId that)) return false;
        return Objects.equals(user, that.user) &&
                Objects.equals(roleName, that.roleName);
    }
    @Override public int hashCode() { return Objects.hash(user, roleName); }
}


