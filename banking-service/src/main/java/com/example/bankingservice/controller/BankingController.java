package com.example.bankingservice.controller;

import com.example.bankingservice.model.TransactionRequest;
import com.example.bankingservice.model.TransactionResponse;
import com.example.bankingservice.service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ba")
public class BankingController {
    @Autowired
    private BankingService bankingService;

    @PostMapping("/debit")
    public TransactionResponse debitMoney(@RequestBody TransactionRequest transactionRequest) {
        return bankingService.debitFromBankingAccount(transactionRequest);
    }
}
