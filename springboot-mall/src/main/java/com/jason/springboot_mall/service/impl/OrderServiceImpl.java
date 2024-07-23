package com.jason.springboot_mall.service.impl;

import com.jason.springboot_mall.dao.OrderDao;
import com.jason.springboot_mall.dao.ProductDao;
import com.jason.springboot_mall.dao.UserDao;
import com.jason.springboot_mall.dto.BuyItem;
import com.jason.springboot_mall.dto.CreateOrderRequest;
import com.jason.springboot_mall.model.Order;
import com.jason.springboot_mall.model.OrderItem;
import com.jason.springboot_mall.model.Product;
import com.jason.springboot_mall.model.User;
import com.jason.springboot_mall.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService
{
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public Order getOrderById(Integer orderId)
    {
        Order order=orderDao.getOrderById(orderId);
        List<OrderItem> orderItemList=orderDao.getOrderItemByOrderId(orderId);
        order.setOrderItemList(orderItemList);
        return order;
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest)
    {
        // 檢查user 是否存在
        User user=userDao.getUserById(userId);
        if(user== null)
        {
            log.warn("該 userId {} 不存在 ", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


        int totalAmount=0;
        List<OrderItem> orderItemList=new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList())
        {
            Product product=productDao.getProductById(buyItem.getProductId());

            // 檢查 product 是否存在、庫存是否足夠
            if(product==null)
            {
                log.warn("商品 {} 不存在", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            else if (product.getStock() < buyItem.getQuantity())
            {
                log.warn("商品{} 庫存數量不足，無法購買。剩餘庫存 {} ， 欲購買數量{}", buyItem.getProductId(),
                        product.getStock(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

            }
            productDao.updateStock(product.getProductId(), product.getStock() - buyItem.getQuantity());


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
