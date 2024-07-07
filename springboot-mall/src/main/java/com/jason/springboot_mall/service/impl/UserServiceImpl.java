package com.jason.springboot_mall.service.impl;

import com.jason.springboot_mall.dao.UserDao;
import com.jason.springboot_mall.dto.UserLoginRequest;
import com.jason.springboot_mall.dto.UserRegisterRequest;
import com.jason.springboot_mall.model.User;
import com.jason.springboot_mall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService
{

    private final static Logger log= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    // create 要返回Integer
    // 命名成 register 而不命名成 createUser 因為不是單純去實作創建帳號而已
    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {

        // 檢查註冊的email
        User user=userDao.getUserByEmail(userRegisterRequest.getEmail());

        if (user != null)
        {
            log.warn("該 email {} 已經被註冊", userRegisterRequest.getEmail());
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 使用 MD5 生成密碼的雜湊值
        String hashedPassword=
                DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashedPassword);
        // 創建帳號
        // create 要返回Integer
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user=userDao.getUserByEmail(userLoginRequest.getEmail());
        // 檢查user 是否存在
        if (user== null)
        {
            log.warn("該 email {} 尚未註冊", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        // 使用 MD5 生成密碼的雜湊值
        String hashedPassword=
                DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());

        // 比較密碼
        if(user.getPassword().equals(hashedPassword))
        {
            return user;
        }
        else
        {
            log.warn("該 email {} 的密碼不正確", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        }
    }
}
