package com.teckmils.warehousemanagementsystem.domain.category.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

public record CategoryResponse(
        @NotNull UUID id,
        @NotBlank String name,
        @NotBlank String description,
        Timestamp createdAt,
        Timestamp updatedAt
        ) {
}
