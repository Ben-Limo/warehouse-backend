package com.teckmils.warehousemanagementsystem.domain.product.controller;

import com.teckmils.warehousemanagementsystem.domain.product.model.Product;
import com.teckmils.warehousemanagementsystem.domain.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public List<Product> getProducts() {
        return this.productService.getProducts();
    }

    @GetMapping("/products/{id}")
    public Optional<Product> getProductById(@PathVariable UUID id) {
        return this.productService.getProductById(id);
    }
}
