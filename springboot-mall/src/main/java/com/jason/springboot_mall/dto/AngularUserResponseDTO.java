package com.jason.springboot_mall.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
@Schema(title = "用戶回應參數DTO")
public class AngularUserResponseDTO {
    @Schema(title = "用戶Id")
    private Integer userId;
    @Schema(title = "用戶Email")
    @Email
    @NotBlank
    private String email;
    @Schema(title = "用戶密碼")
    @NotBlank
    @JsonIgnore
    private String password;

    @Schema(title = "用戶建立時間")
    private Date createdDate;
    @Schema(title = "用戶修改時間")
    private Date lastModifiedDate;
    @Schema(title = "用戶角色")
    private String roles;
    @Schema(title = "Token")
    private String accessToken;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
