package com.example.creditcardservice.service;


import com.example.creditcardservice.model.TransactionRequest;
import com.example.creditcardservice.model.TransactionResponse;

public interface CreditCardService {
    TransactionResponse debitFromCreditCard(TransactionRequest transactionRequest);
}
