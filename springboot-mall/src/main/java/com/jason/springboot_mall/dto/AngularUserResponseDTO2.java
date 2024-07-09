package com.jason.springboot_mall.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

// 暫時不用這個class 2024/7/9 9:21
public class AngularUserResponseDTO2
{
    @Email
    @NotBlank
    private String email;

    @JsonIgnore
    private String password;

    private Date createdDate;
    private Date lastModifiedDate;

    private String roles;
    private String accessToken;





}
