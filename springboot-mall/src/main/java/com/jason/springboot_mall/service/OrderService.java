package com.jason.springboot_mall.service;

import com.jason.springboot_mall.dto.CreateOrderRequest;
import com.jason.springboot_mall.model.Order;

public interface OrderService
{
    Order getOrderById(Integer orderId);
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);





}
