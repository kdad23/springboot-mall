package com.jason.springboot_mall.service;

import com.jason.springboot_mall.dao.ProductDao;
import com.jason.springboot_mall.dto.ProductRequest;
import com.jason.springboot_mall.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService
{
    @Autowired
    ProductDao productDao;
    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }
}
