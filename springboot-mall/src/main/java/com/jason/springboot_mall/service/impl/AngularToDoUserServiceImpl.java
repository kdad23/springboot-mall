package com.jason.springboot_mall.service.impl;

import com.jason.springboot_mall.dao.AngularToDOUserDao;
import com.jason.springboot_mall.dto.AngularUserLoginRequestDTO;
import com.jason.springboot_mall.dto.AngularUserResponseDTO;
import com.jason.springboot_mall.dto.AngularUserRegisterRequestDTO;
import com.jason.springboot_mall.model.AngularToDoUser;
import com.jason.springboot_mall.model.AngularUserForJPA;
import com.jason.springboot_mall.dao.AngularUserRepository;
import com.jason.springboot_mall.service.AngularToDoUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AngularToDoUserServiceImpl implements AngularToDoUserService {


    private final static Logger log= LoggerFactory.getLogger(AngularToDoUserServiceImpl.class);
    @Autowired
    private AngularToDOUserDao angularToDoUserDao;
    @Autowired
    private AngularUserRepository angularUserRepository;

    @Override
    public AngularUserResponseDTO getUserById(Integer userId) {

        AngularUserResponseDTO angularUserResponseDTO=new AngularUserResponseDTO();
        BeanUtils.copyProperties(angularToDoUserDao.getUserById(userId), angularUserResponseDTO);
        return angularUserResponseDTO;
    }

    @Override
    public Integer register(AngularUserRegisterRequestDTO angularUserRegisterRequestDTO) {

        AngularToDoUser angularToDoUser=new AngularToDoUser();
        BeanUtils.copyProperties(angularUserRegisterRequestDTO, angularToDoUser);

        // 檢查註冊的email
        AngularToDoUser angularToDoUser2 =
                angularToDoUserDao.getUserByEmail(angularToDoUser.getEmail());

        if (angularToDoUser2 != null)
        {
            log.warn("該 email {} 已經被註冊", angularToDoUser2.getEmail());
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 使用 MD5 生成密碼的雜湊值
        String hashedPassword=
                DigestUtils.md5DigestAsHex(angularUserRegisterRequestDTO.getPassword().getBytes());
        angularUserRegisterRequestDTO.setPassword(hashedPassword);
        BeanUtils.copyProperties(angularUserRegisterRequestDTO, angularToDoUser);

        // 創建帳號
        // create 要返回Integer
        return angularToDoUserDao.createUser(angularToDoUser);
    }

    @Override
    public AngularUserResponseDTO login(AngularUserLoginRequestDTO angularUserLoginRequestDTO) {

        AngularUserResponseDTO angularUserResponseDTO=new AngularUserResponseDTO();

        AngularToDoUser angularToDoUser=new AngularToDoUser();
        BeanUtils.copyProperties(angularUserLoginRequestDTO, angularToDoUser);

        AngularToDoUser angularToDoUser1 = angularToDoUserDao.getUserByEmail(angularToDoUser.getEmail());
        // 檢查user 是否存在
        if (angularToDoUser1 == null)
        {
            log.warn("該 email {} 尚未註冊", angularUserLoginRequestDTO.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        // 使用 MD5 生成密碼的雜湊值
        String hashedPassword=
                DigestUtils.md5DigestAsHex(angularUserLoginRequestDTO.getPassword().getBytes());

        // 比較密碼
        if(angularToDoUser1.getPassword().equals(hashedPassword))
        {
            BeanUtils.copyProperties(angularToDoUser1, angularUserResponseDTO);
            return angularUserResponseDTO;
        }
        else
        {
            log.warn("該 email {} 的密碼不正確", angularUserLoginRequestDTO.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        }
    }


    @Override
    public Integer registerByJPA(AngularUserRegisterRequestDTO angularUserRegisterRequestDTO)
    {
        AngularUserForJPA angularUserForJPA =new AngularUserForJPA();
        BeanUtils.copyProperties(angularUserRegisterRequestDTO, angularUserForJPA);

        // 檢查註冊的email
        AngularUserForJPA angularUserForJPA2 =
                angularUserRepository.findByEmail(angularUserForJPA.getEmail());

        if (angularUserForJPA2 != null)
        {
            log.warn("該 email {} 已經被註冊", angularUserRegisterRequestDTO.getEmail());
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 使用 MD5 生成密碼的雜湊值
        String hashedPassword=
                DigestUtils.md5DigestAsHex(angularUserRegisterRequestDTO.getPassword().getBytes());

        angularUserRegisterRequestDTO.setPassword(hashedPassword);
        AngularUserForJPA angularUserForJPA3 = new AngularUserForJPA();
        BeanUtils.copyProperties(angularUserRegisterRequestDTO, angularUserForJPA3);
        // 創建帳號
        // create 要返回Integer
        angularUserRepository.save(angularUserForJPA3);

        AngularUserForJPA angularUserForJPA4 =
                angularUserRepository.findByEmail(angularUserForJPA3.getEmail());

        return angularUserForJPA4.getUserId();

    }


    @Override
    public AngularUserResponseDTO getUserByIdByJPA(Integer userId)
    {
        AngularUserForJPA angularUserForJPA =
                angularUserRepository.findById(userId).orElse(null);

        AngularUserResponseDTO angularUserResponseDTO=new AngularUserResponseDTO();

        assert angularUserForJPA != null;
        BeanUtils.copyProperties(angularUserForJPA, angularUserResponseDTO);
        return angularUserResponseDTO;

    }
    @Override
    public AngularUserResponseDTO loginByJPA(AngularUserLoginRequestDTO angularUserLoginRequestDTO)
    {
        AngularUserResponseDTO angularUserResponseDTO=new AngularUserResponseDTO();

        AngularUserForJPA angularUserForJPA =new AngularUserForJPA();
        BeanUtils.copyProperties(angularUserLoginRequestDTO, angularUserForJPA);

        // 檢查註冊的email
        AngularUserForJPA angularUserForJPA2 =
                angularUserRepository.findByEmail(angularUserForJPA.getEmail());

        // 檢查user 是否存在
        if (angularUserForJPA2 == null)
        {
            log.warn("該 email {} 尚未註冊", angularUserLoginRequestDTO.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        // 使用 MD5 生成密碼的雜湊值
        String hashedPassword=
                DigestUtils.md5DigestAsHex(angularUserLoginRequestDTO.getPassword().getBytes());

        // 比較密碼
        if(angularUserForJPA2.getPassword().equals(hashedPassword))
        {
            BeanUtils.copyProperties(angularUserForJPA2, angularUserResponseDTO);
            return angularUserResponseDTO;
        }
        else
        {
            log.warn("該 email {} 的密碼不正確", angularUserLoginRequestDTO.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        }
    }




}
