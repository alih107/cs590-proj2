package com.example.paypalservice.service;


import com.example.paypalservice.model.TransactionRequest;
import com.example.paypalservice.model.TransactionResponse;

public interface PaypalService {
    TransactionResponse debitFromPaypal(TransactionRequest transactionRequest, String authorizationHeader);
}
