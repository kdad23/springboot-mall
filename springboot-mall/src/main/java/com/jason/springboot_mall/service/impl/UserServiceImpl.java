package com.jason.springboot_mall.service.impl;

import com.jason.springboot_mall.dao.UserDao;
import com.jason.springboot_mall.dto.UserRegisterRequest;
import com.jason.springboot_mall.model.User;
import com.jason.springboot_mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }
}
