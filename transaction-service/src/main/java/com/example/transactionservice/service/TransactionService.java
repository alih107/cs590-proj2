package com.example.transactionservice.service;

import com.example.transactionservice.model.TransactionRequest;
import com.example.transactionservice.model.TransactionResponse;

public interface TransactionService {
    TransactionResponse debitFromCard(TransactionRequest request);
}
