package com.jason.springboot_mall.dao;

import com.jason.springboot_mall.model.Order;
import com.jason.springboot_mall.model.OrderItem;

import java.util.List;

public interface OrderDao
{
    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemByOrderId(Integer orderId);
    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
