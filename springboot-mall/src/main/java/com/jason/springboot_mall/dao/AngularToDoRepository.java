package com.jason.springboot_mall.dao;

import com.jason.springboot_mall.model.AngularToDoForJPA;
import com.jason.springboot_mall.model.AngularUserForJPA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AngularToDoRepository extends JpaRepository<AngularToDoForJPA, Integer>
{
    AngularToDoForJPA findByTitle(String title);
    List<AngularToDoForJPA> findByTitleLike(String title);

    //AngularToDoForJPA findById(Integer id);
    //AngularToDoForJPA findByNoteId(Integer id);


}
