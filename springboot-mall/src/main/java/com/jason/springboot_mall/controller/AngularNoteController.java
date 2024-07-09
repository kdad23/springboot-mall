package com.jason.springboot_mall.controller;

import com.jason.springboot_mall.dto.AngularNoteRequest;
import com.jason.springboot_mall.model.AngularNote;
import com.jason.springboot_mall.service.AngularToDoNoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AngularNoteController
{

    @Autowired
    private AngularToDoNoteService angularToDoNoteService;
    @PostMapping("/angular/note/add")
    public ResponseEntity<AngularNote> add(@RequestBody @Valid AngularNoteRequest angular_noteRequest)
    {
        Integer noteId= angularToDoNoteService.createNote(angular_noteRequest);
        // 返回新增的資料物件
        AngularNote angular_note= angularToDoNoteService.getNoteById(noteId);

        return ResponseEntity.status(HttpStatus.CREATED).body(angular_note);
    }
    @GetMapping("/angular/notes")
    public ResponseEntity<List<AngularNote>> getNotes()
    {
        //取得 Angular_Note list
        List<AngularNote> noteList = angularToDoNoteService.getNotes();
        return ResponseEntity.status(HttpStatus.OK).body(noteList);
    }

    @GetMapping("/angular/notes/{noteTitle}")
    public ResponseEntity<List<AngularNote>> getNote(@PathVariable String noteTitle)
    {
        AngularNote angular_note= angularToDoNoteService.getNoteByTitle(noteTitle);
        List<AngularNote> x=new ArrayList<>();
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
        angularToDoNoteService.deleteNoteById(noteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


    @PutMapping("/angular/note/{noteId}")
    public ResponseEntity<AngularNote> updateNote(@PathVariable Integer noteId,
                                                  @RequestBody @Valid AngularNoteRequest angular_noteRequest)
    {
        //檢查note是否存在
        AngularNote angular_note= angularToDoNoteService.getNoteById(noteId);
        if(angular_note ==null)
        {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // 修改數據
        angularToDoNoteService.updateNote(noteId, angular_noteRequest);
        // 返回新增的資料物件
        AngularNote angular_note1= angularToDoNoteService.getNoteById(noteId);

        return ResponseEntity.status(HttpStatus.CREATED).body(angular_note1);


    }



}
