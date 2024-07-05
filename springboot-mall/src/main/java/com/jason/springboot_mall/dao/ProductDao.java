package com.jason.springboot_mall.dao;

import com.jason.springboot_mall.model.Product;

public interface ProductDao
{
    Product getProductById(Integer productId);
}
