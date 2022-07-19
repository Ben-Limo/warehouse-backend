package com.teckmils.warehousemanagementsystem.data.transaction.service;


import java.util.List;
import java.util.Optional;

import com.teckmils.warehousemanagementsystem.data.transaction.model.Transaction;
import com.teckmils.warehousemanagementsystem.data.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Optional<Transaction> getTransactionByID(Long id) {
        return this.transactionRepository.findById(id);
    }
}
