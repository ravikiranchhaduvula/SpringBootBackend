package com.example.service;

import com.example.entity.Product;
import com.example.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class ProductServiceProgramatic {

    private final ProductRepository productRepository;
    private final TransactionTemplate transactionTemplate;

    public ProductServiceProgramatic(ProductRepository productRepository, PlatformTransactionManager transactionManager) {
        this.productRepository = productRepository;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }

    public void addTwoProductsProgrammatically() {
        transactionTemplate.executeWithoutResult(status -> {
            try {
                Product p1 = new Product(null, "Phone", 500.0);
                Product p2 = new Product(null, "Tablet", 800.0);

                productRepository.save(p1);
                productRepository.save(p2);

                // Simulate error
                if (true) throw new RuntimeException("Force rollback");
            } catch (Exception e) {
                status.setRollbackOnly(); // Manually trigger rollback
                throw e; // Optional: rethrow for controller to catch
            }
        });
    }
}
