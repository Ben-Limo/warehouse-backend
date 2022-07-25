package com.teckmils.warehousemanagementsystem.domain.transaction.controller;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.teckmils.warehousemanagementsystem.domain.transaction.model.Transaction;
import com.teckmils.warehousemanagementsystem.domain.transaction.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return this.transactionService.getTransactions();
    }

    @GetMapping("/transactions/{id}")
    public Optional<Transaction> getTransaction(@PathVariable UUID id) {
        return this.transactionService.getTransactionByID(id);
    }
}
