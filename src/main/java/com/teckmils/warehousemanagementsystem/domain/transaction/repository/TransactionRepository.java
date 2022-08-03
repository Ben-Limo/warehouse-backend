package com.teckmils.warehousemanagementsystem.domain.transaction.repository;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.teckmils.warehousemanagementsystem.domain.transaction.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, UUID> {
    Optional<Transaction> findById(UUID id);

    List<Transaction> findAll();

    void deleteById(@NonNull UUID uid);
}
