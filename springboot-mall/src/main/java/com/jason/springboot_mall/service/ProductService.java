package com.jason.springboot_mall.service;

import com.jason.springboot_mall.dto.ProductRequest;
import com.jason.springboot_mall.model.Product;

public interface ProductService
{
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);

}
