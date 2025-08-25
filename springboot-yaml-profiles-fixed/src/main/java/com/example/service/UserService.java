package com.example.service;

import com.example.DTO.CreateUserRequest;
import com.example.DTO.UserDTO;
import com.example.entity.User;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class UserService {
    private final UserRepository userRepository;
    public UserDTO getById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));

        // map entity → DTO
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.isEnabled()
        );
    }
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        System.out.println("Initializing UserService Bean");
    }

    public String getGreeting() {
        return "Hello from UserService (@Component)";
    }

    @PostConstruct
    public void init() {
        // Simulate post-construction setup
        System.out.println("PostConstruct: Initializing config for UserService...");
    }

    @PreDestroy
    public void cleanup() {
        // Cleanup logic
        System.out.println("PreDestroy: UserService is being destroyed. Cleaning up...");
    }

    public UserDTO create(CreateUserRequest req) {
        var entity = new User();
        entity.setName(req.name());
        entity.setEmail(req.email());
        entity.setPassword(req.password()); // normally encode before saving
        entity.setEnabled(true);

        var saved = userRepository.save(entity);
        return new UserDTO(saved.getId(), saved.getName(), saved.getEmail(), saved.isEnabled());
    }
}
