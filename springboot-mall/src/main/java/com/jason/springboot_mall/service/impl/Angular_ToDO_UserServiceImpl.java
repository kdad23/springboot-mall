package com.jason.springboot_mall.service.impl;

import com.jason.springboot_mall.dao.Angular_ToDO_UserDao;
import com.jason.springboot_mall.dao.UserDao;
import com.jason.springboot_mall.dto.Angular_UserLoginRequest;
import com.jason.springboot_mall.dto.Angular_UserRegisterRequest;
import com.jason.springboot_mall.dto.UserLoginRequest;
import com.jason.springboot_mall.dto.UserRegisterRequest;
import com.jason.springboot_mall.model.Angular_ToDO_User;
import com.jason.springboot_mall.model.User;
import com.jason.springboot_mall.service.Angular_ToDO_UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
public class Angular_ToDO_UserServiceImpl implements Angular_ToDO_UserService {


    private final static Logger log= LoggerFactory.getLogger(Angular_ToDO_UserServiceImpl.class);
    @Autowired
    private Angular_ToDO_UserDao angular_toDO_userDao;
    @Override
    public Angular_ToDO_User getUserById(Integer userId) {
        return angular_toDO_userDao.getUserById(userId);
    }

    @Override
    public Integer register(Angular_UserRegisterRequest angular_userRegisterRequest) {
        // 檢查註冊的email
        Angular_ToDO_User angular_toDO_user=
                angular_toDO_userDao.getUserByEmail(angular_userRegisterRequest.getEmail());

        if (angular_toDO_user != null)
        {
            log.warn("該 email {} 已經被註冊", angular_userRegisterRequest.getEmail());
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 使用 MD5 生成密碼的雜湊值
        String hashedPassword=
                DigestUtils.md5DigestAsHex(angular_userRegisterRequest.getPassword().getBytes());
        angular_userRegisterRequest.setPassword(hashedPassword);
        // 創建帳號
        // create 要返回Integer
        return angular_toDO_userDao.createUser(angular_userRegisterRequest);
    }

    @Override
    public Angular_ToDO_User login(Angular_UserLoginRequest angular_userLoginRequest) {
        Angular_ToDO_User angular_toDO_user=angular_toDO_userDao.getUserByEmail(angular_userLoginRequest.getEmail());
        // 檢查user 是否存在
        if (angular_toDO_user== null)
        {
            log.warn("該 email {} 尚未註冊", angular_userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        // 使用 MD5 生成密碼的雜湊值
        String hashedPassword=
                DigestUtils.md5DigestAsHex(angular_userLoginRequest.getPassword().getBytes());

        // 比較密碼
        if(angular_toDO_user.getPassword().equals(hashedPassword))
        {
            return angular_toDO_user;
        }
        else
        {
            log.warn("該 email {} 的密碼不正確", angular_userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        }
    }
}
