package com.jason.springboot_mall.dao.impl;

import com.jason.springboot_mall.dao.OrderDao;
import com.jason.springboot_mall.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDaoImpl implements OrderDao
{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public Integer createOrder(Integer userId, Integer totalAmount)
    {
        // order表名要有 `` 雙引號
        String sql="INSERT INTO `order`(user_id, total_amount, created_date, last_modified_date) " +
                " VALUES (:userId, :totalAmount, :createDate, :lastModifiedDate)";

        Map<String, Object> map=new HashMap<>();
        map.put("userId", userId);
        map.put("totalAmount", totalAmount);

        Date now=new Date();
        map.put("createDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder=new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int orderId=keyHolder.getKey().intValue();

        return orderId;
    }


    @Override
    public void createOrderItems(Integer orderId, List<OrderItem> orderItemList)
    {
        //
        String sql="INSERT INTO order_item(order_id, product_id, quantity, " +
                " amount ) " +
                " VALUES (:orderId, :productId, :quantity, :amount)";

        MapSqlParameterSource[] parameterSources=new MapSqlParameterSource[orderItemList.size()];

        for (int i = 0; i < orderItemList.size() ; i++)
        {
            OrderItem orderItem=orderItemList.get(i);

            parameterSources[i]=new MapSqlParameterSource();
            parameterSources[i].addValue("orderId", orderId);
            parameterSources[i].addValue("productId", orderItem.getProductId());
            parameterSources[i].addValue("quantity", orderItem.getQuantity());
            parameterSources[i].addValue("amount", orderItem.getAmount());

        }

        namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);

    }
}
