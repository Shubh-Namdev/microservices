package com.electronicbazaar.product.service;

import com.electronicbazaar.product.model.Product;
import com.electronicbazaar.product.repository.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Product save(Product p) {
        return repo.save(p);
    }

    public Page<Product> list(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Optional<Product> findById(Long id) {
        return repo.findById(id);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
