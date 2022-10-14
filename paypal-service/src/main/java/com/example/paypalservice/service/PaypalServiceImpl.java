package com.example.paypalservice.service;

import com.example.paypalservice.model.TransactionRequest;
import com.example.paypalservice.model.TransactionResponse;
import org.springframework.stereotype.Service;

@Service
public class PaypalServiceImpl implements PaypalService {
    @Override
    public TransactionResponse debitFromPaypal(TransactionRequest request) {
        return new TransactionResponse(String.format("The amount: %s is successfully charged from card: %s via Paypal Service!",
                request.getAmount(), request.getCardNumber()), "COMPLETED");
    }
}
