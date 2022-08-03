package com.teckmils.warehousemanagementsystem.domain.transaction.dto;

import com.teckmils.warehousemanagementsystem.domain.customer.dto.CustomerRespItem;
import com.teckmils.warehousemanagementsystem.domain.product.dto.ProductResponse;
import com.teckmils.warehousemanagementsystem.domain.user.dto.UserResponse;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.UUID;

public record TransactionResponseItem(
        UUID id,
        Collection<ProductResponse> products,
        CustomerRespItem customer,
        UserResponse user,
        Timestamp createdAt,
        Timestamp updateAt
) {
}
