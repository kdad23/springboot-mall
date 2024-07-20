package com.jason.springboot_mall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jason.springboot_mall.constant.ProductCategory;
import com.jason.springboot_mall.dto.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AngularNoteControllerTest
{
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    // 放取回來的AngularToDoDTO
    private AngularToDoDTO toDoList;

    // 查詢商品
//    @Transactional
//    @Test
//    public void getToDoes_success() throws Exception {
//        // 先註冊一個帳號
//        AngularUserRegisterRequestDTO angularUserRegisterRequestDTO=
//                new AngularUserRegisterRequestDTO();
//        angularUserRegisterRequestDTO.setEmail("dddddddddd@gmail.com");
//        angularUserRegisterRequestDTO.setPassword("111111");
//
//        register(angularUserRegisterRequestDTO);
//
//        // 再登入
//        AngularUserLoginRequestDTO angularUserLoginRequestDTO = new AngularUserLoginRequestDTO();
//        angularUserLoginRequestDTO.setEmail(angularUserRegisterRequestDTO.getEmail());
//        angularUserLoginRequestDTO.setPassword(angularUserRegisterRequestDTO.getPassword());
//
//        login(angularUserLoginRequestDTO);
//
//
//
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .get("/angular/notes");
//
//        mockMvc.perform(requestBuilder)
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.note_id", notNullValue()))
//                .andExpect(jsonPath("$.title",  notNullValue()))
//                .andExpect(jsonPath("$.description", notNullValue()))
//                .andExpect(jsonPath("$.createdDate", notNullValue()))
//                .andExpect(jsonPath("$.lastModifiedDate", notNullValue()));
//    }



    // 查詢商品
    @Test
    public void getNotesSuccess() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/angular/notes/get/{id}", 1);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
            ;

    }
    @Transactional
    @Test
    public void getNotesNotFound() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/angular/notes/{noteTitle}", "!!!!!!");

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(404));
    }

    // 創建商品
    @Transactional
    @Test
    public void createNotesSuccess() throws Exception {
        AngularNoteRequest angularNoteRequest=new AngularNoteRequest();
        angularNoteRequest.setTitle("測試新增功能");
        angularNoteRequest.setDescription("測試");

        String json = objectMapper.writeValueAsString(angularNoteRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/angular/notes/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.note_id", notNullValue()))
                .andExpect(jsonPath("$.title",  notNullValue()))
                .andExpect(jsonPath("$.description", notNullValue()))
                .andExpect(jsonPath("$.createdDate", notNullValue()))
                .andExpect(jsonPath("$.lastModifiedDate", notNullValue()));
    }





    @Transactional
    @Test
    public void updateNotesSuccess() throws Exception {
        AngularNoteRequest angularNoteRequest = new AngularNoteRequest();
        angularNoteRequest.setNote_id(1);
        angularNoteRequest.setTitle("測試更新Title");
        angularNoteRequest.setDescription("測試更新description");


        String json = objectMapper.writeValueAsString(angularNoteRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/angular/notes/{noteId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.note_id", notNullValue()))
                .andExpect(jsonPath("$.title",  notNullValue()))
                .andExpect(jsonPath("$.description", notNullValue()))
                .andExpect(jsonPath("$.createdDate", notNullValue()))
                .andExpect(jsonPath("$.lastModifiedDate", notNullValue()));
    }







    // 刪除商品
    @Transactional
    @Test
    public void deleteNotesSuccess() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/angular/notes/{noteId}", 1);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(204));
    }





    // 註冊功能
    private void register(AngularUserRegisterRequestDTO angularUserRegisterRequestDTO) throws Exception {
        String json = objectMapper.writeValueAsString(angularUserRegisterRequestDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/angular/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201));
    }

    // 登入功能
    private void login(AngularUserLoginRequestDTO angularUserLoginRequestDTO) throws Exception {
        String json = objectMapper.writeValueAsString(angularUserLoginRequestDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/angular/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200));
    }





//    @BeforeEach
    private void registerAndLogin() throws Exception {
        AngularUserRegisterRequestDTO angularUserRegisterRequestDTO = new AngularUserRegisterRequestDTO();
        angularUserRegisterRequestDTO.setEmail("dddddddddd@gmail.com");
        angularUserRegisterRequestDTO.setPassword("111111");

        String json = this.objectMapper.writeValueAsString(angularUserRegisterRequestDTO);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/angular/users/register").contentType(MediaType.APPLICATION_JSON).content(json);


    }





    // 登入功能
//    @BeforeEach
    private void login2() throws Exception {

        AngularUserLoginRequestDTO angularUserLoginRequestDTO = new AngularUserLoginRequestDTO();
        angularUserLoginRequestDTO.setEmail("bbb@gmail.com");
        angularUserLoginRequestDTO.setPassword("111111");

        String json2 = this.objectMapper.writeValueAsString(angularUserLoginRequestDTO);

        RequestBuilder requestBuilder =
                MockMvcRequestBuilders.post("/angular/users/login").contentType(MediaType.APPLICATION_JSON).content(json2);
        this.mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().is(201));
    }






}