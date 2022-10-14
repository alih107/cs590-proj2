package com.example.transactionservice.controller;

import com.example.transactionservice.model.TransactionRequest;
import com.example.transactionservice.model.TransactionResponse;
import com.example.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/debit")
    public TransactionResponse debitMoney(@RequestBody TransactionRequest transactionRequest) {
        return transactionService.debitFromCard(transactionRequest);
    }
}
