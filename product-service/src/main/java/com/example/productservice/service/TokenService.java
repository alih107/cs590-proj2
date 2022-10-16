package com.example.productservice.service;

public interface TokenService {
    boolean verifyToken(String authorizationHeader);
}
