package com.teckmils.warehousemanagementsystem.domain.product.service;

import com.teckmils.warehousemanagementsystem.domain.product.model.Product;
import com.teckmils.warehousemanagementsystem.domain.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return this.productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return this.productRepository.findById(id);
    }
}
