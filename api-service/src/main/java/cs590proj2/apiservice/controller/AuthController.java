package cs590proj2.apiservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/login")
    public String login() {
        return "login successful";
    }

    @PostMapping("/register")
    public String register() {
        return "register successful";
    }
}
