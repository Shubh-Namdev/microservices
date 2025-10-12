package com.electronicbazaar.service;

import com.electronicbazaar.model.Product;
import com.electronicbazaar.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Product save(Product p) {
        return repo.save(p);
    }

    public List<Product> list() {
        return repo.findAll();
    }

    public Product get(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Page<Product> list(Pageable pageable) {
    return repo.findAll(pageable);
}
}
