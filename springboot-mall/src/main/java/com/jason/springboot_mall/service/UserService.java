package com.jason.springboot_mall.service;

import com.jason.springboot_mall.dto.UserLoginRequest;
import com.jason.springboot_mall.dto.UserRegisterRequest;
import com.jason.springboot_mall.model.User;

public interface UserService
{
    User getUserById(Integer userId);
    Integer register(UserRegisterRequest userRegisterRequest);

    User login(UserLoginRequest userLoginRequest);

}
