package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.exception.LowQuantityException;
import com.example.productservice.model.ProductDto;
import com.example.productservice.model.ProductQuantityDto;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductDto productDto);
    Product addQuantity(ProductQuantityDto productQuantityDto);
    Product reduceQuantity(ProductQuantityDto productQuantityDto) throws LowQuantityException;
    List<Product> getAll();
}
