// src/main/java/com/example/controller/UserDynamicNativeController.java
package com.example.controller;

import com.example.DTO.UserDTO;
import com.example.service.UserDynamicNativeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/native-search")
public class UserDynamicNativeController {

    private final UserDynamicNativeService service;

    public UserDynamicNativeController(UserDynamicNativeService service) {
        this.service = service;
    }

    // Example:
    // GET /api/users/native-search?q=ra&domain=gmail.com&sortBy=name&dir=asc&page=0&size=5
    @GetMapping
    public List<UserDTO> search(
            @RequestParam(name = "q", required = false) String nameContains,
            @RequestParam(required = false) String domain,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String dir,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        return service.search(nameContains, domain, sortBy, dir, page, size);
    }
}
