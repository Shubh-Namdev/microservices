package com.crio.buy_buddy_product_service.service;

import java.util.List;

import com.crio.buy_buddy_product_service.model.Product;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product productDetails);
    void deleteProduct(Long id);
}
