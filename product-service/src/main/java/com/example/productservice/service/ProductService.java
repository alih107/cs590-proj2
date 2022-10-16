package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.model.ProductDto;
import com.example.productservice.model.ProductQuantityDto;
import com.example.productservice.model.ResultDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<Product> createProduct(ProductDto productDto, String authorizationHeader);
    ResponseEntity<Product> addQuantity(ProductQuantityDto productQuantityDto, String authorizationHeader);
    ResponseEntity<ResultDto> reduceQuantity(ProductQuantityDto productQuantityDto, String authorizationHeader);
    List<Product> getAll();
}
