package cs590proj2.apiservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @GetMapping
    public String getAllOrders() {
        return "all orders";
    }

    @PostMapping
    public String createOrder() {
        return "order created";
    }
}
