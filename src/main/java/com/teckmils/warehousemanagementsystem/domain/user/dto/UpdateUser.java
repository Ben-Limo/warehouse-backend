package com.teckmils.warehousemanagementsystem.domain.user.dto;

import javax.validation.constraints.NotBlank;

public record UpdateUser(@NotBlank String role, @NotBlank String email) {
}
