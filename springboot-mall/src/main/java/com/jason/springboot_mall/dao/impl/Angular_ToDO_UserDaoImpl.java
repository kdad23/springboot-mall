package com.jason.springboot_mall.dao.impl;

import com.jason.springboot_mall.dao.Angular_ToDO_UserDao;
import com.jason.springboot_mall.dto.Angular_UserRegisterRequest;
import com.jason.springboot_mall.dto.UserRegisterRequest;
import com.jason.springboot_mall.model.Angular_ToDO_User;
import com.jason.springboot_mall.model.User;
import com.jason.springboot_mall.rowmapper.Angular_UserRowMappper;
import com.jason.springboot_mall.rowmapper.UserRowMappper;
import com.jason.springboot_mall.service.Angular_ToDO_UserService;
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
public class Angular_ToDO_UserDaoImpl implements Angular_ToDO_UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public Angular_ToDO_User getUserById(Integer userId)
    {
        String sql="SELECT  user_id, email, password, created_date,         last_modified_date , " +
                "roles, accessToken " +
                " FROM Angular_ToDo_user WHERE user_id= :userId ";

        Map<String, Object> map=new HashMap<>();
        map.put("userId", userId);

        List<Angular_ToDO_User> userList = namedParameterJdbcTemplate.query(sql, map, new Angular_UserRowMappper());

        if(!userList.isEmpty())
        {
            return userList.get(0);
        }
        else
        {
            return null;
        }
    }

    @Override
    public Angular_ToDO_User getUserByEmail(String email) {
        String sql="SELECT  user_id, email, password, created_date, last_modified_date , roles, accessToken " +
                " FROM Angular_ToDo_user WHERE email=:email ";

        Map<String, Object>map=new HashMap<>();
        map.put("email", email);
        List<Angular_ToDO_User> userList = namedParameterJdbcTemplate.query(sql, map, new Angular_UserRowMappper());

        if(!userList.isEmpty())
        {
            return userList.get(0);
        }
        else
        {
            return null;
        }
    }

    @Override
    public Integer createUser(Angular_UserRegisterRequest angular_userRegisterRequest) {
        String sql="INSERT INTO  Angular_ToDo_user( email, password, created_date, last_modified_date ) " +
                " VALUES ( :email, :password, :createdDate, :lastModifiedDate )";

        Map<String, Object> map=new HashMap<>();
        map.put("email", angular_userRegisterRequest.getEmail());
        map.put("password", angular_userRegisterRequest.getPassword());

        Date now=new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder=new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int userId=keyHolder.getKey().intValue();
        return userId;
    }
}
