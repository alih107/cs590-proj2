package com.example.orderservice.service;

import com.example.orderservice.model.OrderRequest;
import com.example.orderservice.model.OrderResponse;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<OrderResponse> placeOrder(String authHeader, OrderRequest orderRequest);

}
