package com.teckmils.warehousemanagementsystem.domain.product.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.UUID;

public record AddProductItem(
        @NotBlank String name,
        @NotNull Double price,
        @NotNull UUID categoryId,
        @NotEmpty Collection<AddProductsMaterialItem> materials,
        @NotBlank String imageURL
        ) {
}
