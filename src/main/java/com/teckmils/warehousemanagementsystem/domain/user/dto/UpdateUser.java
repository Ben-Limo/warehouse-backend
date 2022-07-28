package com.teckmils.warehousemanagementsystem.domain.user.dto;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public record UpdateUser(@NotBlank String firstName,
                         @NotBlank String lastName,
                         @NotBlank UUID manager,
                         @NotBlank UUID store,
                         @NotBlank String role,
                         @NotBlank String email) {
}
