package com.electronicbazaar.product.controller;

import com.electronicbazaar.product.dto.ProductDTO;
import com.electronicbazaar.product.model.Product;
import com.electronicbazaar.product.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;
    private final ModelMapper mapper;

    public ProductController(ProductService service, ModelMapper mapper) {
        this.service = service; this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto) {
        Product p = mapper.map(dto, Product.class);
        Product saved = service.save(p);
        return ResponseEntity.ok(mapper.map(saved, ProductDTO.class));
    }

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> products = service.list(pageable);
        Page<ProductDTO> dtoPage = products.map(p -> mapper.map(p, ProductDTO.class));
        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> get(@PathVariable Long id) {
        Product p = service.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return ResponseEntity.ok(mapper.map(p, ProductDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
