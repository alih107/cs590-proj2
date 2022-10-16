package com.example.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_products")
public class OrderProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private Long productId;
    private int productQuantity;
    private double productPrice;

    public OrderProducts(Long orderId, Long productId, int productQuantity, double productPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
    }
}
