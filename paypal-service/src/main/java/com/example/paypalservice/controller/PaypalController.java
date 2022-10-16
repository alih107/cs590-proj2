package com.example.paypalservice.controller;

import com.example.paypalservice.model.TransactionRequest;
import com.example.paypalservice.model.TransactionResponse;
import com.example.paypalservice.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pp")
public class PaypalController {
    @Autowired
    private PaypalService transactionService;

    @PostMapping("/debit")
    public TransactionResponse debitMoney(@RequestBody TransactionRequest transactionRequest, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        return transactionService.debitFromPaypal(transactionRequest, authorizationHeader);
    }
}
