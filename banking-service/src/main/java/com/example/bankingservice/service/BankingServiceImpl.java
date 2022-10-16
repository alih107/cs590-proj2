package com.example.bankingservice.service;


import com.example.bankingservice.model.TransactionRequest;
import com.example.bankingservice.model.TransactionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BankingServiceImpl implements BankingService {
    @Value("${rest.api-secret}")
    private String apiSecret;

    public TransactionResponse debitFromBankingAccount(TransactionRequest request, String authorizationHeader) {
        if (!authorizationHeader.equals(apiSecret)) {
            return new TransactionResponse("Invalid api secret", "REJECTED");
        }
        return new TransactionResponse(String.format("The amount: %s is successfully charged from card: %s via Banking Account Service!",
                request.getAmount(), request.getCardNumber()), "COMPLETED");
    }
}
