package com.example.bankingservice.controller;

import com.example.bankingservice.model.TransactionRequest;
import com.example.bankingservice.model.TransactionResponse;
import com.example.bankingservice.service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ba")
public class BankingController {
    @Autowired
    private BankingService bankingService;

    @PostMapping("/debit")
    public TransactionResponse debitMoney(@RequestBody TransactionRequest transactionRequest, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        return bankingService.debitFromBankingAccount(transactionRequest, authorizationHeader);
    }
}
