package com.jason.springboot_mall.service;

import com.jason.springboot_mall.dto.AngularUserLoginRequestDTO;
import com.jason.springboot_mall.dto.AngularUserResponseDTO;
import com.jason.springboot_mall.dto.AngularUserRegisterRequestDTO;

public interface AngularToDoUserService {
    AngularUserResponseDTO getUserById(Integer userId);
    Integer register(AngularUserRegisterRequestDTO angular_userRegisterRequest);

    AngularUserResponseDTO login(AngularUserLoginRequestDTO angularUserLoginRequestDTO);



    Integer registerByJPA(AngularUserRegisterRequestDTO angularUserRegisterRequestDTO);

    AngularUserResponseDTO getUserByIdByJPA(Integer userId);


    AngularUserResponseDTO loginByJPA(AngularUserLoginRequestDTO angularUserLoginRequestDTO);



}
