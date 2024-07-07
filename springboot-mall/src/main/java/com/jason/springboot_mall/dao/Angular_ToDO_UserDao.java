package com.jason.springboot_mall.dao;

import com.jason.springboot_mall.dto.Angular_UserRegisterRequest;
import com.jason.springboot_mall.dto.UserRegisterRequest;
import com.jason.springboot_mall.model.Angular_ToDO_User;
import com.jason.springboot_mall.model.User;

public interface Angular_ToDO_UserDao {
    Angular_ToDO_User getUserById(Integer userId);

    Angular_ToDO_User getUserByEmail(String email);
    Integer createUser(Angular_UserRegisterRequest angular_userRegisterRequest);
}
