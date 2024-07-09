package com.jason.springboot_mall.dao;

import com.jason.springboot_mall.dto.AngularUserRegisterRequestDTO;
import com.jason.springboot_mall.model.AngularToDoUser;

public interface AngularToDOUserDao {
    AngularToDoUser getUserById(Integer userId);

    AngularToDoUser getUserByEmail(String email);
    Integer createUser(AngularToDoUser angularToDoUser);
}
