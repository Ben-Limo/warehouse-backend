package com.teckmils.warehousemanagementsystem.domain.material.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public record AddMaterialStockItem(@NotBlank String name, @Min(0) long stock) {
}
