package com.teckmils.warehousemanagementsystem.data.product.service;

import com.teckmils.warehousemanagementsystem.data.product.model.Product;
import com.teckmils.warehousemanagementsystem.data.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        Iterable<Product> products = this.productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        products.forEach(product -> {productList.add(product);});
        return productList;
    }
}
