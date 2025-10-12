package com.electronicbazaar.product.repository;

import com.electronicbazaar.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
