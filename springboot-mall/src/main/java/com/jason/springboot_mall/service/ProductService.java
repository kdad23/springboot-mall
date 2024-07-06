package com.jason.springboot_mall.service;

import com.jason.springboot_mall.constant.ProductCategory;
import com.jason.springboot_mall.dto.ProductQueryParams;
import com.jason.springboot_mall.dto.ProductRequest;
import com.jason.springboot_mall.model.Product;

import java.util.List;

public interface ProductService
{
    Integer countProduct(ProductQueryParams productQueryParams);
    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer productId);
    Integer createProduct(ProductRequest productRequest);
    void updateProduct(Integer productId, ProductRequest productRequest);
    void deleteProductById(Integer productId);

}
