package com.jason.springboot_mall.controller;

import com.jason.springboot_mall.aop.ReNewJwt;
import com.jason.springboot_mall.dto.AngularNoteRequest;
import com.jason.springboot_mall.dto.AngularToDoDTO;
import com.jason.springboot_mall.model.AngularToDo;
import com.jason.springboot_mall.model.AngularToDoForJPA;
import com.jason.springboot_mall.service.AngularToDoNoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Tag(name="待辦事項", description = "待辦事項的CRUD")
@RestController
public class AngularNoteController
{

    @Autowired
    private AngularToDoNoteService angularToDoNoteService;

    // 放取回來的AngularToDoDTO
    private AngularToDoDTO toDoList;

    @Operation(summary = "新增待辦事項")
    @PostMapping("/angular/notes/add")
    public ResponseEntity<AngularToDoDTO> add(@RequestBody @Valid AngularNoteRequest angularNoteRequest)
    {
        // JDBC 用
//        Integer noteId= angularToDoNoteService.createNote(angularNoteRequest);
//        // 返回新增的資料物件
//        AngularToDo angular_note= angularToDoNoteService.getNoteById(noteId);
//        return ResponseEntity.status(HttpStatus.CREATED).body(angular_note);


        // JPA 用
        Integer noteId=angularToDoNoteService.createToDoByJPA(angularNoteRequest);
        AngularToDoDTO angularToDoDTO= angularToDoNoteService.getToDoByIdByJPA(noteId);
        return ResponseEntity.status(HttpStatus.CREATED).body(angularToDoDTO);

    }
    @ReNewJwt
    @Operation(summary = "取得待辦事項", description = "")
    @GetMapping("/angular/notes")
    public ResponseEntity<List<AngularToDoDTO>> getNotes()
    {
        // JDBC 用
        //取得 Angular_Note list
//        List<AngularToDo> noteList = angularToDoNoteService.getNotes();
//        return ResponseEntity.status(HttpStatus.OK).body(noteList);


        // JPA 用
        List<AngularToDoDTO> toDoList = angularToDoNoteService.getToDosByJPA();
        return ResponseEntity.status(HttpStatus.OK).body(toDoList);


    }
    @Operation(summary = "使用Title取得待辦事項", description = "需要傳入Title參數")
    @GetMapping("/angular/notes/{noteTitle}")
    public ResponseEntity<List<AngularToDoDTO>> getNote(@PathVariable @Parameter(description =
            "note的Title")String noteTitle)
    {
//        AngularToDo angular_note= angularToDoNoteService.getNoteByTitle(noteTitle);
//        List<AngularToDo> x=new ArrayList<>();
//        x.add(angular_note);
//        if(angular_note != null)
//        {
//            return ResponseEntity.status(HttpStatus.OK).body(x);
//        }
//        else
//        {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }



/////////////////////////////////////
        // JPA 用
        List<AngularToDoDTO> angularToDoList= angularToDoNoteService.getToDoByTitle("%"+noteTitle+"%");
        if(!angularToDoList.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.OK).body(angularToDoList);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    // 只要確定資料消失不見就表示成功
    @Operation(summary = "使用Id刪除待辦事項", description = "需要傳入待辦事項Id")
    @DeleteMapping("/angular/notes/{noteId}")
    public ResponseEntity<?> deleteToDo( @PathVariable @Parameter(description = "note的Id") Integer noteId)
    {
          // JDBC 用
//        angularToDoNoteService.deleteNoteById(noteId);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        // JPA 用
        angularToDoNoteService.deleteToDoById(noteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();


    }


    @Operation(summary = "使用Id來更新待辦事項", description = "需傳入待辦事項Id")
    @PutMapping("/angular/notes/{noteId}")
    public ResponseEntity<AngularToDoDTO> updateNote(@PathVariable @Parameter(description = "note" +
            "的Id")Integer noteId,
                                                  @RequestBody @Valid AngularNoteRequest angular_noteRequest)
    {
        // JDBC 用
//        //檢查note是否存在
//        AngularToDo angular_note= angularToDoNoteService.getNoteById(noteId);
//        if(angular_note ==null)
//        {
//            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        // 修改數據
//        angularToDoNoteService.updateNote(noteId, angular_noteRequest);
//        // 返回新增的資料物件
//        AngularToDo angular_note1= angularToDoNoteService.getNoteById(noteId);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(angular_note1);


        // JPA 用
        //檢查note是否存在
        AngularToDoDTO angularToDoDTO= angularToDoNoteService.getToDoByIdByJPA(noteId);
        if(angularToDoDTO ==null)
        {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else if (toDoList.getLastModifiedDate().equals(angularToDoDTO.getLastModifiedDate()))
        {
            log.info("要更新的資料可以更新---------");
            // 修改數據
            angularToDoNoteService.updateToDoByJPA(noteId, angular_noteRequest);
            // 返回新增的資料物件
            AngularToDoDTO angularToDoDTO1= angularToDoNoteService.getToDoByIdByJPA(noteId);
            return ResponseEntity.status(HttpStatus.CREATED).body(angularToDoDTO1);

        }
        log.info("資料不可以更新---------");
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

//        // 修改數據
//        angularToDoNoteService.updateToDoByJPA(noteId, angular_noteRequest);
//        // 返回新增的資料物件
//        AngularToDoDTO angularToDoDTO1= angularToDoNoteService.getToDoByIdByJPA(noteId);
//        return ResponseEntity.status(HttpStatus.CREATED).body(angularToDoDTO1);

    }






    @Operation(summary = "使用Id來取得待辦事項", description = "需傳入待辦事項Id")
    @GetMapping("/angular/notes/get/{id}")
    public ResponseEntity<List<AngularToDoDTO>> getToDo(@PathVariable @Parameter(description =
            "note" +
            "的Id")Integer id)
    {

        // JPA 用
        //檢查note是否存在
        AngularToDoDTO angularToDoDTO= angularToDoNoteService.getToDoByIdByJPA(id);
        if(angularToDoDTO ==null)
        {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // 用Id娶回來的資料
        toDoList=angularToDoDTO;
        List<AngularToDoDTO> angularToDoDTOList=new ArrayList<>();
        angularToDoDTOList.add(angularToDoDTO);
        return  ResponseEntity.status(HttpStatus.OK).body(angularToDoDTOList);


    }





}
