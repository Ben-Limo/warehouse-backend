package com.teckmils.warehousemanagementsystem.domain.customer.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record CustomerRespItem(UUID uid,
                               String name,
                               String contact,
                               String phone,
                               String email,
                               String customerType,
                               String city,
                               String country,
                               Timestamp createdAt,
                               Timestamp updatedAt) {
}
