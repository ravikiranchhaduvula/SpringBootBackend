package com.example.service;

import com.example.entity.Product;
import com.example.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional
    public void addTwoProducts() {
        if (repository.existsByName("Phone") || repository.existsByName("Tablet")) {
            return; // Prevent duplicate inserts
        }
        Product p1 = new Product();
        p1.setName("Phone");
        p1.setPrice(500.0);

        Product p2 = new Product();
        p2.setName("Tablet");
        p2.setPrice(800.0);

        repository.save(p1);
        repository.save(p2);

        // Simulate error
        //if (true) throw new RuntimeException("Something went wrong!");
    }
}
