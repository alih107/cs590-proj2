package com.example.orderservice.service;

import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderProducts;
import com.example.orderservice.model.*;
import com.example.orderservice.repository.OrderProductRepository;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Value("${rest.payment-service-url}")
    private String paymentSvcUrl;

    @Value("${rest.api-secret}")
    private String apiToken;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final RestTemplate restTemplate;
    private final TokenService tokenService;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @Override
    public ResponseEntity<OrderResponse> placeOrder(String authHeader, OrderRequest orderRequest) {
        VerifyDto verifyDto = tokenService.verifyToken(authHeader);
        if (verifyDto == null || !verifyDto.isSuccess()) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        long userId = verifyDto.getUserId();
        double totalAmount = computeTotalAmount(orderRequest.getProducts());
        Order order = new Order(orderRequest.getTransactionType(), userId, totalAmount, "PENDING");
        orderRepository.saveAndFlush(order);
        PaymentResponse paymentResponse = processPayment(new PaymentRequest(orderRequest.getCardNumber(), totalAmount, orderRequest.getTransactionType(), order.getId()));
        OrderResponse response = new OrderResponse(paymentResponse.getMessage(), paymentResponse.getStatus());
        if (!Objects.equals(paymentResponse.getStatus(), "COMPLETED"))
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        orderProductRepository.saveAll(mapProductReqToOrder(userId, orderRequest.getProducts()));
        order.setStatus("COMPLETED");
        orderRepository.save(order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private List<OrderProducts> mapProductReqToOrder(Long userId, List<ProductList> products) {
        return products.stream().map(p -> new OrderProducts(userId, p.getProductId(), p.getQuantity(), p.getPrice())).collect(Collectors.toList());
    }

    private double computeTotalAmount(List<ProductList> products) {
        double total = 0;
        for (ProductList p : products) {
            total += p.getQuantity() * p.getPrice();
        }
        return total;
    }

    private PaymentResponse processPayment(PaymentRequest paymentRequest) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, apiToken);
        HttpEntity<PaymentRequest> request = new HttpEntity<>(paymentRequest, headers);
        return circuitBreaker.run(() -> restTemplate.postForObject(paymentSvcUrl + "/payments/process", request, PaymentResponse.class),
                throwable -> new PaymentResponse("Payment service failed", "REJECTED"));
    }
}
