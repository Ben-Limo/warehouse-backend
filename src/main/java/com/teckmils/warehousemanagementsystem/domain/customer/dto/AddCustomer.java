package com.teckmils.warehousemanagementsystem.domain.customer.dto;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;

public record AddCustomer(@NotEmpty Collection<CustomerItem> customers) {
}
