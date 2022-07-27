package com.teckmils.warehousemanagementsystem.domain.authentication.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record RegisterRequest(@NotBlank String name,
                              @Email @NotBlank String email,
                              @NotBlank String password) {
}
