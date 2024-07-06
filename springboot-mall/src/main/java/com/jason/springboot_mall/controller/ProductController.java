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
        // 返回新增的資料物件
        Product product=productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);


    }
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct( @PathVariable Integer productId,
                                                  @RequestBody @Valid ProductRequest productRequest)
    {
        //檢查product是否存在
        Product product=productService.getProductById(productId);
        if(product ==null)
        {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // 修改商品數據
        productService.updateProduct(productId, productRequest);
        // 返回新增的資料物件
        Product updateProduct=productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(updateProduct);


    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct( @PathVariable Integer productId)
    {
        productService.deleteProductById(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }



}
