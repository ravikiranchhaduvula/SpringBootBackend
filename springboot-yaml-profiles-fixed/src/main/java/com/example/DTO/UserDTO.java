package com.example.DTO;

public class UserDTO {
    private final Long id;
    private final String name;
    private final String email;
    private final boolean enabled;

    public UserDTO(Long id, String name, String email, boolean enabled) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.enabled = enabled;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public boolean getEnabled() {return enabled;}
}


