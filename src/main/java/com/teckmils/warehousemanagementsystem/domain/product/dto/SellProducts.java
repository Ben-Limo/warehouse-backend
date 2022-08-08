package com.teckmils.warehousemanagementsystem.domain.product.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.UUID;

public record SellProducts(@NotEmpty Collection<SellProductItem> productsToBeSold,
                           @NotNull UUID customerId,
                           @NotBlank String paymentMethod) {
}
