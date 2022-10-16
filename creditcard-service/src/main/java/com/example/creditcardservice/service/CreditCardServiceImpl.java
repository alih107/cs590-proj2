package com.example.creditcardservice.service;


import com.example.creditcardservice.model.TransactionRequest;
import com.example.creditcardservice.model.TransactionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CreditCardServiceImpl implements CreditCardService {
    @Value("${rest.api-secret}")
    private String apiSecret;

    public TransactionResponse debitFromCreditCard(TransactionRequest request, String authorizationHeader) {
        if (!authorizationHeader.equals(apiSecret)) {
            return new TransactionResponse("Invalid api secret", "REJECTED");
        }
        return new TransactionResponse(String.format("The amount: %s is successfully charged from card: %s via Credit Card Service!",
                request.getAmount(), request.getCardNumber()), "COMPLETED");
    }
}
