package com.teckmils.warehousemanagementsystem.domain.transaction.controller;


import java.util.List;
import java.util.UUID;


import com.teckmils.warehousemanagementsystem.domain.product.dto.SellProducts;
import com.teckmils.warehousemanagementsystem.domain.transaction.dto.TransactionResponseItem;
import com.teckmils.warehousemanagementsystem.domain.transaction.service.TransactionService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class TransactionController {
    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public List<TransactionResponseItem> getTransactions() {
        return this.transactionService.getTransactions();
    }

    @GetMapping("/transactions/{id}")
    public TransactionResponseItem getTransaction(@PathVariable UUID id) {
        return this.transactionService.getTransactionByID(id);
    }

    @PostMapping("/transactions")
    public void createTransaction(
            @RequestBody @Valid SellProducts request,
            Authentication authentication
            ) {

        this.transactionService.createTransaction(
                request,
                authentication);
    }

}
