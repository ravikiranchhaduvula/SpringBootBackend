package com.example.controller;

import com.example.service.UserCacheServiceL1;
import com.example.service.UserCacheServiceL2_A;
import com.example.service.UserCacheServiceL2_B;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cache")
public class CacheController {

    private final UserCacheServiceL1 l1;
    private final UserCacheServiceL2_A a;
    private final UserCacheServiceL2_B b;

    public CacheController(UserCacheServiceL1 l1, UserCacheServiceL2_A a, UserCacheServiceL2_B b) {
        this.l1 = l1;
        this.a = a;
        this.b = b;
    }

    @GetMapping("/l1/{id}")
    public ResponseEntity<String> l1(@PathVariable long id) {
        return ResponseEntity.ok(l1.demoL1(id));
    }

    @GetMapping("/l2/{id}")
    public ResponseEntity<String> l2(@PathVariable long id) {
        var u1 = a.loadOnce(id);   // Tx A (expect SQL)
        var u2 = b.loadAgain(id);  // Tx B (expect no SQL if L2 works)
        return ResponseEntity.ok("L2 cache demo done. u1!=null: " + (u1!=null) + ", u2!=null: " + (u2!=null));
    }
}

