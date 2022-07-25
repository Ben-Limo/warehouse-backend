package com.teckmils.warehousemanagementsystem.domain.material.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record MaterialStockDTO (
        UUID id,
        String name,
        long stock,
        Timestamp createdAt,
        Timestamp updatedAt) {
}
