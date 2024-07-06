package com.jason.springboot_mall.dao;

import com.jason.springboot_mall.dto.UserRegisterRequest;
import com.jason.springboot_mall.model.User;

public interface UserDao {
    User getUserById(Integer userId);
    Integer createUser(UserRegisterRequest userRegisterRequest);
}
