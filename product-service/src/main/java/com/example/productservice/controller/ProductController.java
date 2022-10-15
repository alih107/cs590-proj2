package com.example.productservice.controller;

import com.example.productservice.entity.Product;
import com.example.productservice.exception.LowQuantityException;
import com.example.productservice.model.ProductDto;
import com.example.productservice.model.ProductQuantityDto;
import com.example.productservice.model.ResultDto;
import com.example.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
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
    public Product createProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @PostMapping("/add-quantity")
    public Product addQuantity(@RequestBody ProductQuantityDto productQuantityDto) {
        return productService.addQuantity(productQuantityDto);
    }

    @PostMapping("/reduce-quantity")
    public ResponseEntity<ResultDto> reduceQuantity(@RequestBody ProductQuantityDto productQuantityDto) throws LowQuantityException {
        try {
            productService.reduceQuantity(productQuantityDto);
            return ResponseEntity.ok().body(new ResultDto(true, "success"));
        } catch (LowQuantityException lowQuantityException) {
            return ResponseEntity.badRequest().body(new ResultDto(false, "insufficient quantity"));
        }
    }
}
