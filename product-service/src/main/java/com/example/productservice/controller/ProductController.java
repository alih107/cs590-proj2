package com.example.productservice.controller;

import com.example.productservice.entity.Product;
import com.example.productservice.model.ProductDto;
import com.example.productservice.model.ProductQuantityDto;
import com.example.productservice.model.ResultDto;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        return productService.createProduct(productDto, authorizationHeader);
    }

    @PostMapping("/add-quantity")
    public ResponseEntity<Product> addQuantity(@RequestBody ProductQuantityDto productQuantityDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        return productService.addQuantity(productQuantityDto, authorizationHeader);
    }

    @PostMapping("/reduce-quantity")
    public ResponseEntity<ResultDto> reduceQuantity(@RequestBody ProductQuantityDto productQuantityDto, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        return productService.reduceQuantity(productQuantityDto, authorizationHeader);
    }
}
