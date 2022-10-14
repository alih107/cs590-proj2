package cs590proj2.authservice.controller;

import cs590proj2.authservice.entity.User;
import cs590proj2.authservice.model.LoginDto;
import cs590proj2.authservice.model.LoginResponseDto;
import cs590proj2.authservice.model.RegisterDto;
import cs590proj2.authservice.model.VerifyDto;
import cs590proj2.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/register")
    public User register(@RequestBody RegisterDto registerDto) {
        return authService.register(registerDto);
    }

    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity<LoginResponseDto> signin(@RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }

    @GetMapping
    @RequestMapping("/verify")
    public ResponseEntity<VerifyDto> verify(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        return authService.verify(authorizationHeader);
    }
}
