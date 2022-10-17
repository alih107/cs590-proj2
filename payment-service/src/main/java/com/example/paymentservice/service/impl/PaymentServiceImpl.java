package com.example.paymentservice.service.impl;

import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.model.PaymentRequest;
import com.example.paymentservice.model.PaymentResponse;
import com.example.paymentservice.model.TransactionRequest;
import com.example.paymentservice.model.TransactionResponse;
import com.example.paymentservice.repository.PaymentRepository;
import com.example.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Value("${rest.transaction-service-url}")
    private String transactionSvcUrl;
    @Value("${rest.api-secret}")
    private String apiToken;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;


    @Override
    public PaymentResponse processPayment(String authorizationHeader,PaymentRequest request) {
        if (!authorizationHeader.equals(apiToken)) {
            return new PaymentResponse("Invalid api secret", "REJECTED");
        }
        TransactionRequest transactionRequest = new TransactionRequest(request.getCardNumber(), request.getAmount(), request.getTransactionType());

        TransactionResponse transactionResponse = makeTransaction(transactionRequest);

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setMessage(transactionResponse.getMessage());
        paymentResponse.setStatus(transactionResponse.getStatus());

        Payment payment = new Payment(request.getCardNumber(), request.getAmount(), transactionResponse.getMessage(), transactionResponse.getStatus(), request.getTransactionType(), request.getOrderId(), request.getUserId());
        paymentRepository.save(payment);
        return paymentResponse;
    }

    private TransactionResponse makeTransaction(TransactionRequest transactionRequest) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, apiToken);
        HttpEntity<TransactionRequest> httpRequest = new HttpEntity<>(transactionRequest, headers);
        return circuitBreaker.run(() -> restTemplate.postForObject(transactionSvcUrl + "/transactions/debit", httpRequest, TransactionResponse.class),
                throwable -> new TransactionResponse("Transaction service failed", "REJECTED"));
    }
}
