package com.jason.springboot_mall.controller;

import com.jason.springboot_mall.dto.*;
import com.jason.springboot_mall.service.AngularToDoUserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class AngularToDoController
{
    @Autowired
    private AngularToDoUserService angularToDoUserService;

    private final static Logger log= LoggerFactory.getLogger(AngularToDoController.class);

    @PostMapping("/angular/users/register")
    public ResponseEntity<AngularUserResponseDTO> register(@RequestBody @Valid AngularUserRegisterRequestDTO angularUserRegisterRequestDTO)
    {
        // create 要返回Integer
        Integer userId= angularToDoUserService.register(angularUserRegisterRequestDTO);
        AngularUserResponseDTO angularUserResponseDTO = angularToDoUserService.getUserById(userId);
        log.warn("回傳的angular_toDO_user ========= {} ", angularUserResponseDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(angularUserResponseDTO);
    }


    @PostMapping("/angular/users/login")
    public ResponseEntity<AngularUserResponseDTO> login(@RequestBody @Valid AngularUserLoginRequestDTO angularUserLoginRequestDTO)
    {
        //
        AngularUserResponseDTO angularUserResponseDTO1 = angularToDoUserService.login(angularUserLoginRequestDTO);
        log.warn("login()回傳的angular_toDO_user ========= {} ", angularUserResponseDTO1.toString());

        return ResponseEntity.status(HttpStatus.OK).body(angularUserResponseDTO1);
    }

}
