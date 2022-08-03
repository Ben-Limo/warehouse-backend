package com.teckmils.warehousemanagementsystem.domain.transaction.dto;


import com.teckmils.warehousemanagementsystem.domain.customer.dto.CustomerItem;
import com.teckmils.warehousemanagementsystem.domain.product.dto.SellProducts;

public record CreateTransactionItem(SellProducts productsToBeSold, CustomerItem customerDetails) {
}
