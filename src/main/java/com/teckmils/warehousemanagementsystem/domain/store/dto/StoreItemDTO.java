package com.teckmils.warehousemanagementsystem.domain.store.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

public record StoreItemDTO (
        @NotNull UUID id,
        @NotBlank String storeName,
        @NotBlank String city,
        @NotBlank String country,
        Timestamp createdAt,
        Timestamp updatedAt
){
}
