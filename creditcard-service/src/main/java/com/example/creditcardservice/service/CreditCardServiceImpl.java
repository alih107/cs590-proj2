package com.example.creditcardservice.service;


import com.example.creditcardservice.model.TransactionRequest;
import com.example.creditcardservice.model.TransactionResponse;
import org.springframework.stereotype.Service;

@Service
public class CreditCardServiceImpl implements CreditCardService {
    @Override
    public TransactionResponse debitFromCreditCard(TransactionRequest request) {
        return new TransactionResponse(String.format("The amount: %s is successfully charged from card: %s via Credit Card Service!",
                request.getAmount(), request.getCardNumber()), "COMPLETED");
    }
}
