package com.teckmils.warehousemanagementsystem.domain.product.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public record AddProductsMaterialItem(@NotNull UUID id, @Min(1) long count) {
}
