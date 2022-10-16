package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.model.ProductDto;
import com.example.productservice.model.ProductQuantityDto;
import com.example.productservice.model.ResultDto;
import com.example.productservice.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private TokenService tokenService;

    public ResponseEntity<Product> createProduct(ProductDto productDto, String authorizationHeader) {

        if (!tokenService.verifyToken(authorizationHeader)) {
            return new ResponseEntity<>((Product) null, HttpStatus.UNAUTHORIZED);
        }

        Product product = new Product(
                0,
                productDto.getName(),
                productDto.getPrice(),
                productDto.getQuantity()
        );
        productRepo.save(product);
        return ResponseEntity.ok(product);
    }

    public ResponseEntity<Product> addQuantity(ProductQuantityDto productQuantityDto, String authorizationHeader) {
        if (!tokenService.verifyToken(authorizationHeader)) {
            return new ResponseEntity<>((Product) null, HttpStatus.UNAUTHORIZED);
        }
        Product product = productRepo.findById(productQuantityDto.getId()).get();
        product.setQuantity(product.getQuantity() + productQuantityDto.getQuantity());
        productRepo.save(product);
        return ResponseEntity.ok().body(product);
    }

    public ResponseEntity<ResultDto> reduceQuantity(ProductQuantityDto productQuantityDto, String authorizationHeader) {
        if (!tokenService.verifyToken(authorizationHeader)) {
            return new ResponseEntity<>((ResultDto) null, HttpStatus.UNAUTHORIZED);
        }
        Product product = productRepo.findById(productQuantityDto.getId()).get();
        if (product.getQuantity() < productQuantityDto.getQuantity()) {
            return ResponseEntity.ok().body(new ResultDto(false, "insufficient quantity"));
        }
        product.setQuantity(product.getQuantity() - productQuantityDto.getQuantity());
        if (product.getQuantity() < 10) {
            System.out.println(product.getName() + " quantity is less than 10! Refill please");
        }
        productRepo.save(product);
        return ResponseEntity.ok().body(new ResultDto(true, "success"));
    }

    public List<Product> getAll() {
        return productRepo.findAll();
    }
}
