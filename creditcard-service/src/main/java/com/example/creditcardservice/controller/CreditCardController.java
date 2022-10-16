package com.example.creditcardservice.controller;

import com.example.creditcardservice.model.TransactionRequest;
import com.example.creditcardservice.model.TransactionResponse;
import com.example.creditcardservice.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cc")
public class CreditCardController {
    @Autowired
    private CreditCardService transactionService;

    @PostMapping("/debit")
    public TransactionResponse debitMoney(@RequestBody TransactionRequest transactionRequest, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        return transactionService.debitFromCreditCard(transactionRequest, authorizationHeader);
    }
}
