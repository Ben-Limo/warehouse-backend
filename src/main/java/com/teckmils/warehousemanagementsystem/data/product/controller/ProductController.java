package com.teckmils.warehousemanagementsystem.data.product.controller;

import com.teckmils.warehousemanagementsystem.data.product.model.Product;
import com.teckmils.warehousemanagementsystem.data.product.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
