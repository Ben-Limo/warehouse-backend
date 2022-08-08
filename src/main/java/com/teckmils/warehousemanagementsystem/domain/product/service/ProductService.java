package com.teckmils.warehousemanagementsystem.domain.product.service;

import com.teckmils.warehousemanagementsystem.domain.category.dto.CategoryResponse;
import com.teckmils.warehousemanagementsystem.domain.category.model.Category;
import com.teckmils.warehousemanagementsystem.domain.category.repository.CategoryRepository;
import com.teckmils.warehousemanagementsystem.domain.material.model.Material;
import com.teckmils.warehousemanagementsystem.domain.material.repository.MaterialRepository;
import com.teckmils.warehousemanagementsystem.domain.product.dto.AddProductItem;
import com.teckmils.warehousemanagementsystem.domain.product.dto.ProductResponse;
import com.teckmils.warehousemanagementsystem.domain.product.model.Product;
import com.teckmils.warehousemanagementsystem.domain.product.model.ProductMaterial;
import com.teckmils.warehousemanagementsystem.domain.product.repository.ProductMaterialRepository;
import com.teckmils.warehousemanagementsystem.domain.product.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final MaterialRepository materialRepository;
    private  final CategoryRepository categoryRepository;
    private final ProductMaterialRepository productMaterialRepository;
    public ProductService(ProductRepository productRepository,
                          MaterialRepository materialRepository,
                          CategoryRepository categoryRepository,
                          ProductMaterialRepository productMaterialRepository) {
        this.productRepository = productRepository;
        this.materialRepository = materialRepository;
        this.categoryRepository = categoryRepository;
        this.productMaterialRepository = productMaterialRepository;
    }

    public List<ProductResponse> getProducts() {
        final List<Product> products = this.productRepository.findAll();
        List<ProductResponse> productResponse = new ArrayList<>();
        products.forEach(product -> productResponse.add(new ProductResponse(
                product.getId(),
                product.getName(),
                product.getImageURL(),
                product.getPrice(),
                product.getCalculatedProductStock(),
                new CategoryResponse(
                        product.getCategory().getId(),
                        product.getCategory().getName(),
                        product.getCategory().getDescription(),
                        product.getCategory().getCreatedAt(),
                        product.getCategory().getUpdatedAt()
                ),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getProductMaterials()
        )));
        return productResponse;
    }

    public ProductResponse getProductById(UUID id) {
        final Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        final Category category = product.getCategory();

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getImageURL(),
                product.getPrice(),
                product.getCalculatedProductStock(),
                new CategoryResponse(
                        product.getCategory().getId(),
                        product.getCategory().getName(),
                        product.getCategory().getDescription(),
                        product.getCategory().getCreatedAt(),
                        product.getCategory().getUpdatedAt()
                ),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getProductMaterials()
        );
    }

    public void addProducts(final Collection<AddProductItem> addProdRequest) {
        addProdRequest.forEach(prodRequest -> {
            final Category category = this.categoryRepository.findById(prodRequest.categoryId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry! cant find requested category."));

            final var product = new Product(
                    prodRequest.name(),
                    prodRequest.price(),
                    prodRequest.imageURL(),
                    category
            );

            final Collection<ProductMaterial> productMaterials = prodRequest.materials().stream()
                    .map(materialItem -> {
                        final Material material = this.materialRepository.findById(materialItem.id())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry! cant find requested material."));

                        return new ProductMaterial(product, material, materialItem.count());
                    }).toList();

            this.productRepository.save(product);
            this.productMaterialRepository.saveAll(productMaterials);
        });
    }

    public void deleteProductById(final UUID id) {
        this.productRepository.deleteById(id);
    }
}
