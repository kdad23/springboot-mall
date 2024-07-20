package com.jason.springboot_mall.service;

import com.jason.springboot_mall.dto.CreateOrderRequest;

public interface OrderService
{
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);


}
