package com.jason.springboot_mall.service;

import com.jason.springboot_mall.dto.*;
import com.jason.springboot_mall.model.AngularToDo;

import java.util.List;

public interface AngularToDoNoteService {

    Integer countNote(AngularNoteRequest angular_noteRequest);
    List<AngularToDo> getNotes( );
    AngularToDo getNoteById(Integer noteId);
    AngularToDo getNoteByTitle(String noteTitle);
    Integer createNote(AngularNoteRequest angular_noteRequest);
    void updateNote(Integer noteId, AngularNoteRequest angular_noteRequest);
    void deleteNoteById(Integer noteId);

////////////////////////////////////////////////////////
    //JPA 用

    Integer createToDoByJPA(AngularNoteRequest angular_noteRequest);
    AngularToDoDTO getToDoByIdByJPA(Integer toDoId);
    List<AngularToDoDTO> getToDosByJPA();
    List<AngularToDoDTO> getToDoByTitle(String noteTitle);
    void deleteToDoById(Integer toDoId);

    void updateToDoByJPA(Integer noteId, AngularNoteRequest angular_noteRequest);


}
