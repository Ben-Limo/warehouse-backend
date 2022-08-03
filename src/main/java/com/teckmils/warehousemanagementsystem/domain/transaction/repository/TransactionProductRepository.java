package com.teckmils.warehousemanagementsystem.domain.transaction.repository;

import com.teckmils.warehousemanagementsystem.domain.transaction.model.TransactionProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.Set;
import java.util.UUID;

public interface TransactionProductRepository extends CrudRepository<TransactionProduct, UUID> {
    @NonNull
    Set<TransactionProduct> findAll();
}
