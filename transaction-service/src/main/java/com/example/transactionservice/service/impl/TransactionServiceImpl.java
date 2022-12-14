package com.example.transactionservice.service.impl;

import com.example.transactionservice.model.TransactionRequest;
import com.example.transactionservice.model.TransactionResponse;
import com.example.transactionservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Value("${rest.banking-service-url}")
    private String bankingSvcUrl;
    @Value("${rest.paypal-service-url}")
    private String paypalSvcUrl;
    @Value("${rest.credit-service-url}")
    private String creditSvcUrl;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @Value("${rest.api-secret}")
    private String apiSecret;

    @Override
    public TransactionResponse debitFromCard(TransactionRequest req, String authorizationHeader) {
        if (!authorizationHeader.equals(apiSecret)) {
            return new TransactionResponse("Invalid api secret", "REJECTED");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, apiSecret);

        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");

        HttpEntity<TransactionRequest> request = new HttpEntity<>(req, headers);
        return switch (req.getTransactionType().toUpperCase()) {
            case "PP" -> circuitBreaker.run(() -> restTemplate.postForObject(paypalSvcUrl + "/pp/debit", request, TransactionResponse.class),
                    throwable -> new TransactionResponse("Paypal service failed", "REJECTED"));
            case "CC" -> circuitBreaker.run(() -> restTemplate.postForObject(creditSvcUrl + "/cc/debit", request, TransactionResponse.class),
                    throwable -> new TransactionResponse("Credit card service failed", "REJECTED"));
            case "BA" -> circuitBreaker.run(() -> restTemplate.postForObject(bankingSvcUrl + "/ba/debit", request, TransactionResponse.class),
                    throwable -> new TransactionResponse("Bank account service failed", "REJECTED"));
            default -> new TransactionResponse("Transaction type is invalid", "REJECTED");
        };
    }
}
