package com.example.paypalservice.controller;

import com.example.paypalservice.model.TransactionRequest;
import com.example.paypalservice.model.TransactionResponse;
import com.example.paypalservice.service.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pp")
public class PaypalController {
    @Autowired
    private PaypalService transactionService;

    @PostMapping("/debit")
    public TransactionResponse debitMoney(@RequestBody TransactionRequest transactionRequest) {
        return transactionService.debitFromPaypal(transactionRequest);
    }
}
