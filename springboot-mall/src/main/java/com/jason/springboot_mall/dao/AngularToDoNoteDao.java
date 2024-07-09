package com.jason.springboot_mall.dao;

import com.jason.springboot_mall.dto.AngularNoteRequest;
import com.jason.springboot_mall.model.AngularNote;

import java.util.List;

public interface AngularToDoNoteDao {

    Integer countNote(AngularNoteRequest angular_noteRequest);

    List<AngularNote> getNotes( );
    AngularNote getNoteById(Integer noteId);
    AngularNote getNoteByTitle(String noteTitle);
    Integer createNote(AngularNoteRequest angular_noteRequest);

    void updateNote(Integer noteId, AngularNoteRequest angular_noteRequest);
    void deleteNoteById(Integer noteId);
}
