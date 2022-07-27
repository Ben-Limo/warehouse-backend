package com.teckmils.warehousemanagementsystem.domain.user.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record UserResponse(UUID uid,
                           String userName,
                           String email,
                           String role,
                           Timestamp createdAt,
                           Timestamp updatedAt) {
}

