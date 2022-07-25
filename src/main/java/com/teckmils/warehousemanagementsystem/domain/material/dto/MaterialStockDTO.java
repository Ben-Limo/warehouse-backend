package com.teckmils.warehousemanagementsystem.domain.material.dto;

import java.sql.Timestamp;

public record MaterialStockDTO (
        Long id,
        String name,
        long stock,
        Timestamp createdAt,
        Timestamp updatedAt) {
}
