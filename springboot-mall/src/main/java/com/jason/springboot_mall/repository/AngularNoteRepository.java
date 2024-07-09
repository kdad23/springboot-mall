package com.jason.springboot_mall.repository;

import com.jason.springboot_mall.model.AngularToDoUser;
import com.jason.springboot_mall.model.AngularUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AngularNoteRepository extends JpaRepository<AngularUser, Integer>{
}
