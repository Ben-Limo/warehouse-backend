package com.teckmils.warehousemanagementsystem.domain.transaction.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.teckmils.warehousemanagementsystem.domain.transaction.model.Transaction;
import com.teckmils.warehousemanagementsystem.domain.transaction.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactions() {
        return this.transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionByID(UUID id) {
        return this.transactionRepository.findById(id);
    }
}
