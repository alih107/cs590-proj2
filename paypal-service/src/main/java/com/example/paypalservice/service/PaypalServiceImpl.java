package com.example.paypalservice.service;

import com.example.paypalservice.model.TransactionRequest;
import com.example.paypalservice.model.TransactionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaypalServiceImpl implements PaypalService {
    @Value("${rest.api-secret}")
    private String apiSecret;

    @Override
    public TransactionResponse debitFromPaypal(TransactionRequest request, String authorizationHeader) {
        if (!authorizationHeader.equals(apiSecret)) {
            return new TransactionResponse("Invalid api secret", "REJECTED");
        }
        return new TransactionResponse(String.format("The amount: %s is successfully charged from card: %s via Paypal Service!",
                request.getAmount(), request.getCardNumber()), "COMPLETED");
    }
}
