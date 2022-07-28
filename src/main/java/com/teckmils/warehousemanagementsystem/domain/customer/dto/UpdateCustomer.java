package com.teckmils.warehousemanagementsystem.domain.customer.dto;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public record UpdateCustomer(@NotBlank String name,
                             @NotBlank String contact,
                             @NotBlank String phone,
                             @NotBlank String email,
                             @NotBlank String city,
                             @NotBlank String country,
                             @NotBlank UUID customerType
) {
}
