package com.jason.springboot_mall.controller;

import com.jason.springboot_mall.dto.ProductRequest;
import com.jason.springboot_mall.model.Product;
import com.jason.springboot_mall.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController
{
    @Autowired
    private ProductService productService;
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId)
    {
        System.out.println(123);
        Product product=productService.getProductById(productId);

        if(product != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct( @RequestBody @Valid ProductRequest productRequest)
    {

        Integer productId=productService.createProduct(productRequest);
        Product product=productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);


    }
}
