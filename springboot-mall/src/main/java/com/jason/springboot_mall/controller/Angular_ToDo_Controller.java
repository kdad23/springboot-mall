package com.jason.springboot_mall.controller;

import com.jason.springboot_mall.constant.ProductCategory;
import com.jason.springboot_mall.dto.*;
import com.jason.springboot_mall.model.Angular_ToDO_User;
import com.jason.springboot_mall.model.Product;
import com.jason.springboot_mall.model.User;
import com.jason.springboot_mall.service.Angular_ToDO_UserService;
import com.jason.springboot_mall.service.ProductService;
import com.jason.springboot_mall.service.UserService;
import com.jason.springboot_mall.service.impl.Angular_ToDO_UserServiceImpl;
import com.jason.springboot_mall.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class Angular_ToDo_Controller
{
    @Autowired
    private Angular_ToDO_UserService angular_toDO_userService;

    private final static Logger log= LoggerFactory.getLogger(Angular_ToDo_Controller.class);

    @PostMapping("/angular/users/register")
    public ResponseEntity<Angular_ToDO_User> register(@RequestBody @Valid Angular_UserRegisterRequest angular_userRegisterRequest)
    {
        // create 要返回Integer
        Integer userId=angular_toDO_userService.register(angular_userRegisterRequest);
        Angular_ToDO_User angular_toDO_user=angular_toDO_userService.getUserById(userId);
        log.warn("回傳的angular_toDO_user ========= {} ", angular_toDO_user);

        return ResponseEntity.status(HttpStatus.CREATED).body(angular_toDO_user);
    }


    @PostMapping("/angular/users/login")
    public ResponseEntity<Angular_ToDO_User> login(@RequestBody @Valid Angular_UserLoginRequest angular_userLoginRequest)
    {
        //
        Angular_ToDO_User angular_toDO_user=angular_toDO_userService.login(angular_userLoginRequest);
        log.warn("login()回傳的angular_toDO_user ========= {} ", angular_toDO_user.toString());

        return ResponseEntity.status(HttpStatus.OK).body(angular_toDO_user);
    }

}
