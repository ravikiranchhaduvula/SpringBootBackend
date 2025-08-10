package com.example.controller;

import com.example.service.ProductService;
import com.example.service.ProductServiceProgramatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductServiceProgramatic productServiceProgramatic;

    @GetMapping("/add-two")
    public ResponseEntity<String> addTwo() {
        productService.addTwoProducts();
        return ResponseEntity.ok("Done");
    }

    @GetMapping("/add-two-programmatic")
    public ResponseEntity<String> addTwoProgrammatic() {
        try {
            productServiceProgramatic.addTwoProductsProgrammatically();
            return ResponseEntity.ok("Inserted");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed: " + e.getMessage());
        }
    }
}

