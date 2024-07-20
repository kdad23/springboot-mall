package com.jason.springboot_mall.dao;

import com.jason.springboot_mall.model.AngularUserForJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AngularUserRepository extends JpaRepository<AngularUserForJPA, Integer>
{
    AngularUserForJPA findByEmail(String email);


}
