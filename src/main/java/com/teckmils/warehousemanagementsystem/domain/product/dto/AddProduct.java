package com.teckmils.warehousemanagementsystem.domain.product.dto;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;

public record AddProduct(@NotEmpty Collection<AddProductItem> products) {
}
