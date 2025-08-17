package com.example.controller;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.HATEOASUserConsumer;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    private HATEOASUserConsumer hateoasUserConsumer;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public EntityModel<User> getUser(@PathVariable Long id) {
        User user = User.builder()
                .id(id)
                .name("Ravi")
                .email("ravi@email.com")
                .userRoles(new HashSet<>())
                .build();


        return EntityModel.of(user,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                        .getUser(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                        .updateUser(id, null)).withRel("update"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                        .deleteUser(id)).withRel("delete")
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        // update logic
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        // perform delete logic (or mock it for now)
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(@PageableDefault(size = 5, sort = "id") Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/paged")
    public ResponseEntity<PagedModel<EntityModel<User>>> getPagedUsers(
            @PageableDefault(size = 5) Pageable pageable,
            PagedResourcesAssembler<User> assembler) {

        Page<User> users = userRepository.findAll(pageable);
        return ResponseEntity.ok(assembler.toModel(users));
    }

    @GetMapping("/{id}/consume")
    public ResponseEntity<String> triggerLinkConsumption(@PathVariable Long id) {
        hateoasUserConsumer.consumeUserLinks(id);
        return ResponseEntity.ok("Links consumed successfully for user id: " + id);
    }
}

