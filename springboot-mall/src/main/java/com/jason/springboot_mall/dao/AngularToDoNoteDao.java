package com.jason.springboot_mall.dao;

import com.jason.springboot_mall.dto.AngularNoteRequest;
import com.jason.springboot_mall.model.AngularToDo;

import java.util.List;

public interface AngularToDoNoteDao {

    Integer countNote(AngularNoteRequest angular_noteRequest);

    List<AngularToDo> getNotes( );
    AngularToDo getNoteById(Integer noteId);
    AngularToDo getNoteByTitle(String noteTitle);
    Integer createNote(AngularNoteRequest angular_noteRequest);

    void updateNote(Integer noteId, AngularNoteRequest angular_noteRequest);
    void deleteNoteById(Integer noteId);
}
