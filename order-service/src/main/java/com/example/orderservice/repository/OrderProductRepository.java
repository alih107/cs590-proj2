package com.example.orderservice.repository;

import com.example.orderservice.entity.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProducts, Long> {
}
