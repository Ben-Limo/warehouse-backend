package com.teckmils.warehousemanagementsystem.domain.product.controller;

import com.teckmils.warehousemanagementsystem.domain.product.dto.AddProduct;
import com.teckmils.warehousemanagementsystem.domain.product.dto.AddProductItem;
import com.teckmils.warehousemanagementsystem.domain.product.dto.ProductResponse;
import com.teckmils.warehousemanagementsystem.domain.product.model.Product;
import com.teckmils.warehousemanagementsystem.domain.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
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
    public List<ProductResponse> getProducts() {
        return this.productService.getProducts();
    }

    @GetMapping("/products/{id}")
    public ProductResponse getProductById(@PathVariable UUID id) {
        return this.productService.getProductById(id);
    }

    @PostMapping("/products")
    public void addProducts(@RequestBody @Valid AddProduct request) {
        this.productService.addProducts(request.products());
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable final UUID id) {
        this.productService.deleteProductById(id);
    }
}
