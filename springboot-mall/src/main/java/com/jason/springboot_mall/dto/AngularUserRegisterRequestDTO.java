package com.jason.springboot_mall.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
@Schema(title = "註冊請求參數DTO")
public class AngularUserRegisterRequestDTO {
    @Schema(title = "註冊請求Email")
    @Email
    @NotBlank
    private String email;
    @Schema(title = "註冊請求密碼")
    @NotBlank
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
