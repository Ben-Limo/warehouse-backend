package com.teckmils.warehousemanagementsystem.domain.user.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record UserResponse(UUID uid,
                           String userName,
                           String firstName,
                           String lastName,
                           String email,
                           String role,
                           String store,
                           Timestamp createdAt,
                           Timestamp updatedAt) {
}

