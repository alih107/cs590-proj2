package com.example.orderservice.controller;

import com.example.orderservice.model.OrderRequest;
import com.example.orderservice.model.OrderResponse;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<OrderResponse> placeOrder(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                                    @RequestBody OrderRequest orderRequest) {
        return orderService.placeOrder(authorizationHeader, orderRequest);
    }
}
