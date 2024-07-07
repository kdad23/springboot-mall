package com.jason.springboot_mall.service;

import com.jason.springboot_mall.dto.Angular_UserLoginRequest;
import com.jason.springboot_mall.dto.Angular_UserRegisterRequest;
import com.jason.springboot_mall.dto.UserLoginRequest;
import com.jason.springboot_mall.dto.UserRegisterRequest;
import com.jason.springboot_mall.model.Angular_ToDO_User;
import com.jason.springboot_mall.model.User;

public interface Angular_ToDO_UserService {
    Angular_ToDO_User getUserById(Integer userId);
    Integer register(Angular_UserRegisterRequest angular_userRegisterRequest);

    Angular_ToDO_User login(Angular_UserLoginRequest angular_userLoginRequest);
}
