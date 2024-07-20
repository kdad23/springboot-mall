package com.jason.springboot_mall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jason.springboot_mall.dao.UserDao;
import com.jason.springboot_mall.dto.AngularUserLoginRequestDTO;
import com.jason.springboot_mall.dto.AngularUserRegisterRequestDTO;
import com.jason.springboot_mall.dto.UserLoginRequest;
import com.jason.springboot_mall.dto.UserRegisterRequest;
import com.jason.springboot_mall.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AngularToDoControllerTest
{
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserDao userDao;
    private ObjectMapper objectMapper = new ObjectMapper();

    // 測試註冊帳號是否能成功
    @Transactional
    @Test
    public void registerSuccess() throws Exception {

        AngularUserRegisterRequestDTO angularUserRegisterRequestDTO=
                new AngularUserRegisterRequestDTO();
        angularUserRegisterRequestDTO.setEmail("dddddddddd@gmail.com");
        angularUserRegisterRequestDTO.setPassword("111111");


        String json = objectMapper.writeValueAsString(angularUserRegisterRequestDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/angular/users/register")
                .contentType(MediaType.APPLICATION_JSON) // 加這行才可以在request body傳入json
                .content(json);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.userId", notNullValue()))
                .andExpect(jsonPath("$.email", equalTo("dddddddddd@gmail.com")))
                .andExpect(jsonPath("$.createdDate", notNullValue()))
                .andExpect(jsonPath("$.lastModifiedDate", notNullValue())).
                andExpect(jsonPath("$.accessToken", notNullValue()));

        // 檢查資料庫中的密碼不為明碼
//        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());
//        assertNotEquals(userRegisterRequest.getPassword(), user.getPassword());




    }

    // 測同一個帳號重複註冊
    @Transactional
    @Test
    public void registerEmailAlreadyExist() throws Exception {
        // 先註冊一個帳號
        AngularUserRegisterRequestDTO angularUserRegisterRequestDTO=
                new AngularUserRegisterRequestDTO();
        angularUserRegisterRequestDTO.setEmail("dddddddddd@gmail.com");
        angularUserRegisterRequestDTO.setPassword("111111");

        String json = objectMapper.writeValueAsString(angularUserRegisterRequestDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/angular/users/register")
                .contentType(MediaType.APPLICATION_JSON) // 加這行才可以在request body傳入json
                .content(json);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(201));

        // 再次使用同個 email 註冊
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }
    @Transactional
    @Test
    public void register_invalidEmailFormat() throws Exception {
        // 先註冊一個帳號
        AngularUserRegisterRequestDTO angularUserRegisterRequestDTO=
                new AngularUserRegisterRequestDTO();
        angularUserRegisterRequestDTO.setEmail("1213212313");
        angularUserRegisterRequestDTO.setPassword("111111");

        String json = objectMapper.writeValueAsString(angularUserRegisterRequestDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/angular/users/register")
                .contentType(MediaType.APPLICATION_JSON) // 加這行才可以在request body傳入json
                .content(json);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(400));
    }

    // 測試登入是否能成功
    @Transactional
    @Test
    public void loginSuccess() throws Exception {
        // 先註冊一個帳號
        AngularUserRegisterRequestDTO angularUserRegisterRequestDTO=
                new AngularUserRegisterRequestDTO();
        angularUserRegisterRequestDTO.setEmail("dddddddddd@gmail.com");
        angularUserRegisterRequestDTO.setPassword("111111");

        register(angularUserRegisterRequestDTO);

        // 再測試登入功能
        AngularUserLoginRequestDTO angularUserLoginRequestDTO = new AngularUserLoginRequestDTO();
        angularUserLoginRequestDTO.setEmail(angularUserRegisterRequestDTO.getEmail());
        angularUserLoginRequestDTO.setPassword(angularUserRegisterRequestDTO.getPassword());

        String json = objectMapper.writeValueAsString(angularUserLoginRequestDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/angular/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.userId", notNullValue()))
                .andExpect(jsonPath("$.email", equalTo(angularUserRegisterRequestDTO.getEmail())))
                .andExpect(jsonPath("$.createdDate", notNullValue()))
                .andExpect(jsonPath("$.lastModifiedDate", notNullValue()));
    }

    // 測試用錯誤的密碼去登入看會不會失敗
    @Transactional
    @Test
    public void loginWongPassword() throws Exception {
        // 先註冊一個帳號
        AngularUserRegisterRequestDTO angularUserRegisterRequestDTO=
                new AngularUserRegisterRequestDTO();
        angularUserRegisterRequestDTO.setEmail("dddddddddd@gmail.com");
        angularUserRegisterRequestDTO.setPassword("fdjghlfjglskjfklsdj");

        register(angularUserRegisterRequestDTO);

        // 測試密碼輸入錯誤的情況
        AngularUserLoginRequestDTO angularUserLoginRequestDTO = new AngularUserLoginRequestDTO();
        angularUserLoginRequestDTO.setEmail(angularUserRegisterRequestDTO.getEmail());
        angularUserLoginRequestDTO.setPassword("unknown");

        String json = objectMapper.writeValueAsString(angularUserLoginRequestDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/angular/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    // 測試用沒註冊過的email去登入是否會失敗
    @Transactional
    @Test
    public void loginEmailNotExist() throws Exception {
        AngularUserLoginRequestDTO angularUserLoginRequestDTO = new AngularUserLoginRequestDTO();
        angularUserLoginRequestDTO.setEmail("unknown@gmail.com");
        angularUserLoginRequestDTO.setPassword("unknown");


        String json = objectMapper.writeValueAsString(angularUserLoginRequestDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/angular/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }


    private void register(AngularUserRegisterRequestDTO angularUserRegisterRequestDTO) throws Exception {
        String json = objectMapper.writeValueAsString(angularUserRegisterRequestDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/angular/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201));
    }




}