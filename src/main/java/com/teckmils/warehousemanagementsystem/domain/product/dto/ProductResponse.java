package com.teckmils.warehousemanagementsystem.domain.product.dto;

import com.teckmils.warehousemanagementsystem.domain.category.dto.CategoryResponse;
import com.teckmils.warehousemanagementsystem.domain.product.model.ProductMaterial;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        String imageURL,
        Double price,
        long stock,
        CategoryResponse category,
        Timestamp createdAt,
        Timestamp updatedAt,
        Collection<ProductMaterial> materials
) {
}
