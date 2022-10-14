package com.example.bankingservice.service;


import com.example.bankingservice.model.TransactionRequest;
import com.example.bankingservice.model.TransactionResponse;
import org.springframework.stereotype.Service;

@Service
public class BankingServiceImpl implements BankingService {
    @Override
    public TransactionResponse debitFromBankingAccount(TransactionRequest request) {
        return new TransactionResponse(String.format("The amount: %s is successfully charged from card: %s via Banking Account Service!",
                request.getAmount(), request.getCardNumber()), "COMPLETED");
    }
}
