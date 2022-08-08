package com.teckmils.warehousemanagementsystem.domain.transaction.repository;

import com.teckmils.warehousemanagementsystem.domain.transaction.model.PaymentMethod;
import com.teckmils.warehousemanagementsystem.domain.user.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentMethodRepository extends CrudRepository<PaymentMethod, UUID> {
    Optional<PaymentMethod> findByItemName(String itemName);
}
