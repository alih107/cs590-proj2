package com.example.productservice.service;

import com.example.productservice.model.VerifyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${rest.auth-service-url}")
    private String authSvcUrl;

    public boolean verifyToken(String authorizationHeader) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<VerifyDto> response = restTemplate.exchange(
                authSvcUrl + "/auth/verify",
                HttpMethod.GET,
                requestEntity,
                VerifyDto.class);

        VerifyDto verifyDto = response.getBody();
        return (verifyDto != null && verifyDto.isSuccess());
    }
}
