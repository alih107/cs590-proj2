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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${rest.transaction-service-url}")
    private String transactionSvcUrl;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PaymentRepository paymentRepository;


    @Override
    public PaymentResponse processPayment(PaymentRequest request, Long userId) {
        TransactionRequest transactionRequest = new TransactionRequest(request.getCardNumber(), request.getAmount(), request.getTransactionType());

        TransactionResponse transactionResponse = restTemplate.postForObject(transactionSvcUrl + "/transactions/debit", transactionRequest, TransactionResponse.class);

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setMessage(transactionResponse.getMessage());
        paymentResponse.setStatus(transactionResponse.getStatus());

        Payment payment = new Payment(request.getCardNumber(), request.getAmount(), transactionResponse.getMessage(), transactionResponse.getStatus(), request.getTransactionType(), request.getOrderId(), userId);
        paymentRepository.save(payment);
        return paymentResponse;
    }
}
