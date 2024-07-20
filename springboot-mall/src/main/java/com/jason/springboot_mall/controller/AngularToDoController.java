package com.jason.springboot_mall.controller;

import com.jason.springboot_mall.aop.ReNewJwt;
import com.jason.springboot_mall.dto.*;
import com.jason.springboot_mall.service.AngularToDoUserService;
import com.jason.springboot_mall.util.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@Tag(name="用戶", description = "用戶的登入和註冊")
@Validated
@RestController
public class AngularToDoController
{
    @Autowired
    private AngularToDoUserService angularToDoUserService;

    private final static Logger log= LoggerFactory.getLogger(AngularToDoController.class);

    @Operation(summary = "用戶註冊")
    @PostMapping("/angular/users/register")
    public ResponseEntity<AngularUserResponseDTO> register(@RequestBody @Valid AngularUserRegisterRequestDTO angularUserRegisterRequestDTO, HttpServletResponse response)
    {
        // 使用JDBC
        // create 要返回Integer
//        Integer userId= angularToDoUserService.register(angularUserRegisterRequestDTO);
//        AngularUserResponseDTO angularUserResponseDTO = angularToDoUserService.getUserById(userId);
//        log.warn("回傳的angular_toDO_user ========= {} ", angularUserResponseDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(angularUserResponseDTO);


        // 使用JPA
        Integer userId= angularToDoUserService.registerByJPA(angularUserRegisterRequestDTO);
        AngularUserResponseDTO angularUserResponseDTO = angularToDoUserService.getUserByIdByJPA(userId);

        Map<String , Object> claims = new HashMap<>();
        claims.put("userId",angularUserResponseDTO.getUserId());
        claims.put("email", angularUserResponseDTO.getEmail());
        claims.put("password",angularUserResponseDTO.getPassword());
        claims.put("createdDate",angularUserResponseDTO.getCreatedDate());
        claims.put("lastModifiedDate",angularUserResponseDTO.getLastModifiedDate());
        claims.put("accessToken",angularUserResponseDTO.getAccessToken());
        claims.put("roles",angularUserResponseDTO.getRoles());

        //使用JWT，生成令牌
        // generateJwt() 參數要放入自訂義的訊息
        String jwt = JwtUtils.generateJwt(claims);
        angularUserResponseDTO.setAccessToken(jwt);


        // 為Cookie 設置 HttpOnly
        Cookie myCookie = new Cookie("tokenFromJava", jwt);
//        myCookie.setPath(request.getContextPath());
        myCookie.setPath("/");
        myCookie.setHttpOnly(true);
        myCookie.setMaxAge(1000);
        response.addCookie(myCookie);
        log.warn("-----註冊成功------");

        return ResponseEntity.status(HttpStatus.CREATED).body(angularUserResponseDTO);


    }


//    @ReNewJwt
    @Operation(summary = "用戶登入")
    @PostMapping("/angular/users/login")
    public ResponseEntity<AngularUserResponseDTO> login(@RequestBody @Valid AngularUserLoginRequestDTO angularUserLoginRequestDTO, HttpServletRequest request,
    HttpServletResponse response)
    {
        // 使用JDBC
//        AngularUserResponseDTO angularUserResponseDTO1 = angularToDoUserService.login(angularUserLoginRequestDTO);
//        log.warn("login()回傳的angular_toDO_user ========= {} ", angularUserResponseDTO1.toString());
//        return ResponseEntity.status(HttpStatus.OK).body(angularUserResponseDTO1);


        // 使用JPA
        AngularUserResponseDTO angularUserResponseDTO =
                angularToDoUserService.loginByJPA(angularUserLoginRequestDTO);



        Map<String , Object> claims = new HashMap<>();
        claims.put("userId",angularUserResponseDTO.getUserId());
        claims.put("email", angularUserResponseDTO.getEmail());
        claims.put("password",angularUserResponseDTO.getPassword());
        claims.put("createdDate",angularUserResponseDTO.getCreatedDate());
        claims.put("lastModifiedDate",angularUserResponseDTO.getLastModifiedDate());
        claims.put("accessToken",angularUserResponseDTO.getAccessToken());
        claims.put("roles",angularUserResponseDTO.getRoles());
        //使用JWT，生成令牌
        // generateJwt() 參數要放入自訂義的訊息
        String jwt = JwtUtils.generateJwt(claims);
        angularUserResponseDTO.setAccessToken(jwt);


        // 為Cookie 設置 HttpOnly
        Cookie myCookie = new Cookie("tokenFromJava", jwt);
//        myCookie.setPath(request.getContextPath());
        myCookie.setPath("/");
        myCookie.setHttpOnly(true);
        myCookie.setMaxAge(1000);
        response.addCookie(myCookie);

        return ResponseEntity.status(HttpStatus.OK).body(angularUserResponseDTO);

    }

}
