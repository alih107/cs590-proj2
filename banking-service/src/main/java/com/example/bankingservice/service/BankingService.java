package com.example.bankingservice.service;


import com.example.bankingservice.model.TransactionRequest;
import com.example.bankingservice.model.TransactionResponse;

public interface BankingService {
    TransactionResponse debitFromBankingAccount(TransactionRequest transactionRequest, String authorizationHeader);
}
