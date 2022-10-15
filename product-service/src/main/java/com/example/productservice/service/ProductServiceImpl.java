package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.exception.LowQuantityException;
import com.example.productservice.model.ProductDto;
import com.example.productservice.model.ProductQuantityDto;
import com.example.productservice.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product createProduct(ProductDto productDto) {
        Product product = new Product(
                0,
                productDto.getName(),
                productDto.getPrice(),
                productDto.getQuantity()
        );
        productRepo.save(product);
        return product;
    }
    public Product addQuantity(ProductQuantityDto productQuantityDto) {
        Product product = productRepo.findById(productQuantityDto.getId()).get();
        product.setQuantity(product.getQuantity() + productQuantityDto.getQuantity());
        productRepo.save(product);
        return product;
    }
    public Product reduceQuantity(ProductQuantityDto productQuantityDto) throws LowQuantityException {
        Product product = productRepo.findById(productQuantityDto.getId()).get();
        if (product.getQuantity() < productQuantityDto.getQuantity()) {
            throw new LowQuantityException();
        }
        product.setQuantity(product.getQuantity() - productQuantityDto.getQuantity());
        if (product.getQuantity() < 10) {
            System.out.println(product.getName() + " quantity is less than 10! Refill please");
        }
        productRepo.save(product);
        return product;
    }

    public List<Product> getAll() {
        return productRepo.findAll();
    }
}
