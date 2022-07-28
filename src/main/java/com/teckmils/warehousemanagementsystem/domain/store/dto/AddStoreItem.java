package com.teckmils.warehousemanagementsystem.domain.store.dto;

import javax.validation.constraints.NotBlank;

public record AddStoreItem(@NotBlank String storeName,
                           @NotBlank String city,
                           @NotBlank String country) {
}
