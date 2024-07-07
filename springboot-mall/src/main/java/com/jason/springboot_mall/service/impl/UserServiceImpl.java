package com.jason.springboot_mall.service.impl;

import com.jason.springboot_mall.dao.UserDao;
import com.jason.springboot_mall.dto.UserRegisterRequest;
import com.jason.springboot_mall.model.User;
import com.jason.springboot_mall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService
{

    private final static Logger log= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    // 命名程 register 而不命名成 createUser 因為不是單純去實作創建帳號而已
    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {

        // 檢查註冊的email
        User user=userDao.getUserByEmail(userRegisterRequest.getEmail());

        if (user != null)
        {
            log.warn("該 email {} 已經被註冊", userRegisterRequest.getEmail());
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        // 成功註冊
        return userDao.createUser(userRegisterRequest);
    }
}
