package cs590proj2.authservice.service;

import cs590proj2.authservice.entity.User;
import cs590proj2.authservice.model.LoginDto;
import cs590proj2.authservice.model.LoginResponseDto;
import cs590proj2.authservice.model.RegisterDto;
import cs590proj2.authservice.model.VerifyDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<LoginResponseDto> login(LoginDto loginDto);
    User register(RegisterDto registerDto);
    ResponseEntity<VerifyDto> verify(String authorizationHeader);
}
