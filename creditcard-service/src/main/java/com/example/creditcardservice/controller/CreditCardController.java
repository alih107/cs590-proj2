package com.example.creditcardservice.controller;

import com.example.creditcardservice.model.TransactionRequest;
import com.example.creditcardservice.model.TransactionResponse;
import com.example.creditcardservice.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cc")
public class CreditCardController {
    @Autowired
    private CreditCardService transactionService;

    @PostMapping("/debit")
    public TransactionResponse debitMoney(@RequestBody TransactionRequest transactionRequest) {
        return transactionService.debitFromCreditCard(transactionRequest);
    }
}
