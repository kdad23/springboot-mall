package com.jason.springboot_mall.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.Date;


// JPA用的
//@Data
@Schema(title = "用戶Model")
@Entity
@Table(name="angulartodouser")
public class AngularUserForJPA
{

    @Schema(title = "用戶Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private Integer userId;
    @Schema(title = "用戶姓名")
    @Column(name = "username")
    private String username;
    @Schema(title = "用戶Email")
    @Column(name = "email")
    private String email;
    @Schema(title = "用戶密碼")
    @Column(name = "password")
    @JsonIgnore
    private String password;
    @Schema(title = "建立時間")
    @Column(name = "created_date")
    private Date createdDate=new Date();
    @Schema(title = "修改時間")
    @Column(name = "last_modified_date")
    private Date lastModifiedDate=new Date();

    @Schema(title = "用戶權限角色")
    @Column(name = "roles")
    private String roles;
    @Schema(title = "用戶的Jwt")
    @Column(name = "accesstoken")
    private String accessToken;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
