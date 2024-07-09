package com.jason.springboot_mall.dao.impl;

import com.jason.springboot_mall.dao.AngularToDOUserDao;
import com.jason.springboot_mall.model.AngularToDoUser;
import com.jason.springboot_mall.rowmapper.AngularUserRowMappper;
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
public class AngularToDoUserDaoImpl implements AngularToDOUserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public AngularToDoUser getUserById(Integer userId)
    {
        String sql="SELECT  user_id, email, password, created_date,         last_modified_date , " +
                "roles, accessToken " +
                " FROM Angular_ToDo_user WHERE user_id= :userId ";

        Map<String, Object> map=new HashMap<>();
        map.put("userId", userId);

        List<AngularToDoUser> userList = namedParameterJdbcTemplate.query(sql, map, new AngularUserRowMappper());

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
    public AngularToDoUser getUserByEmail(String email) {
        String sql="SELECT  user_id, email, password, created_date, last_modified_date , roles, accessToken " +
                " FROM Angular_ToDo_user WHERE email=:email ";

        Map<String, Object>map=new HashMap<>();
        map.put("email", email);
        List<AngularToDoUser> userList = namedParameterJdbcTemplate.query(sql, map, new AngularUserRowMappper());

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
    public Integer createUser(AngularToDoUser angularToDoUser) {
        String sql="INSERT INTO  Angular_ToDo_user( email, password, created_date, last_modified_date ) " +
                " VALUES ( :email, :password, :createdDate, :lastModifiedDate )";

        Map<String, Object> map=new HashMap<>();
        map.put("email", angularToDoUser.getEmail());
        map.put("password", angularToDoUser.getPassword());

        Date now=new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder=new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int userId=keyHolder.getKey().intValue();
        return userId;
    }
}
