package com.example.controller;

import com.example.DTO.UserDTO;
import com.example.repository.UserDTORepository;
import com.example.repository.UserRepository;
import com.example.repository.UserSummaryRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/dto")
public class UserDtoController {

    private final UserRepository repo;
    private final UserDTORepository dtoRepo;
    private final UserSummaryRepository summaryRepo;

    public UserDtoController(UserRepository repo, UserDTORepository dtoRepo, UserSummaryRepository summaryRepo) {
        this.repo = repo;
        this.dtoRepo = dtoRepo;
        this.summaryRepo = summaryRepo;
    }

    // GET /api/users/dto/by-name?name=Ravi
    @GetMapping("/by-name")
    public List<UserDTO> byName(@RequestParam String name) {
        return repo.findUserDetails(name);
    }

    // GET /api/users/dto/search?q=ra&page=0&size=5&sort=name,asc
    @GetMapping("/search")
    public Object search(@RequestParam(required = false) String q,
                         @RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "5") int size) {
        return dtoRepo.searchAsDto(q, PageRequest.of(page, size));
    }

    // GET /api/users/dto/summaries?q=ra&page=0&size=5
    @GetMapping("/summaries")
    public Object summaries(@RequestParam(required = false) String q,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "5") int size) {
        return summaryRepo.findSummaries(q, PageRequest.of(page, size));
    }
}
