package com.teckmils.warehousemanagementsystem.domain.transaction.controller;


import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


import com.teckmils.warehousemanagementsystem.domain.product.dto.SellProducts;
import com.teckmils.warehousemanagementsystem.domain.transaction.dto.TransactionResponseItem;
import com.teckmils.warehousemanagementsystem.domain.transaction.service.TransactionService;
import org.springframework.format.annotation.DateTimeFormat;
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

    @GetMapping(value = "/transactions")
    public List<TransactionResponseItem> getTransactions(
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
            ) {
        return this.transactionService.getTransactions(
                Timestamp.valueOf(startDate), Timestamp.valueOf(endDate));
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
