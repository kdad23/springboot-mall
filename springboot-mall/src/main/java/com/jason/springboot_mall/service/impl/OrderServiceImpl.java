package com.jason.springboot_mall.service.impl;

import com.jason.springboot_mall.dao.OrderDao;
import com.jason.springboot_mall.dao.ProductDao;
import com.jason.springboot_mall.dto.BuyItem;
import com.jason.springboot_mall.dto.CreateOrderRequest;
import com.jason.springboot_mall.model.OrderItem;
import com.jason.springboot_mall.model.Product;
import com.jason.springboot_mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService
{
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest)
    {
        int totalAmount=0;
        List<OrderItem> orderItemList=new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList())
        {
            Product product=productDao.getProductById(buyItem.getProductId());
            //計算個別商品總價錢
            int amount= buyItem.getQuantity() * product.getPrice();
            //計算總價錢
            totalAmount= totalAmount + amount;

            // 轉換 BuyItem to OrderItem
            OrderItem orderItem=new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);
            orderItemList.add(orderItem);
        }
        // 創建訂單
        Integer orderId=orderDao.createOrder(userId, totalAmount);
        // 有修改多張Table一定要在方法上新增 @Transactional
        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }
}
