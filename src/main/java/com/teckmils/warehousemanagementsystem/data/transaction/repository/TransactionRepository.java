package com.teckmils.warehousemanagementsystem.data.transaction.repository;


import java.util.List;
import java.util.Optional;

import com.teckmils.warehousemanagementsystem.data.transaction.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    Optional<Transaction> findById(Long id);

    List<Transaction> findAll();
}
