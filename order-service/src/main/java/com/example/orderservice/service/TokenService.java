package com.example.orderservice.service;

import com.example.orderservice.model.VerifyDto;

public interface TokenService {
    VerifyDto verifyToken(String authorizationHeader);
}
