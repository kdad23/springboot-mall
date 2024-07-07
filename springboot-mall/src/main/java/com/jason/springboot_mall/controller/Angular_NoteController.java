package com.jason.springboot_mall.controller;

import com.jason.springboot_mall.constant.ProductCategory;
import com.jason.springboot_mall.dto.Angular_NoteRequest;
import com.jason.springboot_mall.dto.Angular_UserRegisterRequest;
import com.jason.springboot_mall.dto.ProductQueryParams;
import com.jason.springboot_mall.dto.ProductRequest;
import com.jason.springboot_mall.model.Angular_Note;
import com.jason.springboot_mall.model.Angular_ToDO_User;
import com.jason.springboot_mall.model.Product;
import com.jason.springboot_mall.service.Angular_ToDo_NoteService;
import com.jason.springboot_mall.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Angular_NoteController
{

    @Autowired
    private Angular_ToDo_NoteService angular_toDo_noteService;
    @PostMapping("/angular/note/add")
    public ResponseEntity<Angular_Note> add(@RequestBody @Valid Angular_NoteRequest angular_noteRequest)
    {
        Integer noteId=angular_toDo_noteService.createNote(angular_noteRequest);
        // 返回新增的資料物件
        Angular_Note angular_note=angular_toDo_noteService.getNoteById(noteId);

        return ResponseEntity.status(HttpStatus.CREATED).body(angular_note);
    }
    @GetMapping("/angular/notes")
    public ResponseEntity<List<Angular_Note>> getNotes()
    {
        //取得 Angular_Note list
        List<Angular_Note> noteList =angular_toDo_noteService.getNotes();
        return ResponseEntity.status(HttpStatus.OK).body(noteList);
    }

    @GetMapping("/angular/notes/{noteTitle}")
    public ResponseEntity<List<Angular_Note>> getNote(@PathVariable String noteTitle)
    {
        Angular_Note angular_note=angular_toDo_noteService.getNoteByTitle(noteTitle);
        List<Angular_Note> x=new ArrayList<>();
        x.add(angular_note);
        if(angular_note != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(x);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }




    // 只要確定資料消失不見就表示成功
    @DeleteMapping("/angular/{noteId}")
    public ResponseEntity<?> deleteProduct( @PathVariable Integer noteId)
    {
        angular_toDo_noteService.deleteNoteById(noteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


    @PutMapping("/angular/note/{noteId}")
    public ResponseEntity<Angular_Note> updateNote( @PathVariable Integer noteId,
                                                  @RequestBody @Valid Angular_NoteRequest angular_noteRequest)
    {
        //檢查product是否存在
        Angular_Note angular_note=angular_toDo_noteService.getNoteById(noteId);
        if(angular_note ==null)
        {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // 修改商品數據
        angular_toDo_noteService.updateNote(noteId, angular_noteRequest);
        // 返回新增的資料物件
        Angular_Note angular_note1=angular_toDo_noteService.getNoteById(noteId);

        return ResponseEntity.status(HttpStatus.CREATED).body(angular_note1);


    }



}
