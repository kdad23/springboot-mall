package com.jason.springboot_mall.service;

import com.jason.springboot_mall.dto.*;
import com.jason.springboot_mall.model.AngularNote;

import java.util.List;

public interface AngularToDoNoteService {

    Integer countNote(AngularNoteRequest angular_noteRequest);
    List<AngularNote> getNotes( );
    AngularNote getNoteById(Integer noteId);
    AngularNote getNoteByTitle(String noteTitle);
    Integer createNote(AngularNoteRequest angular_noteRequest);
    void updateNote(Integer noteId, AngularNoteRequest angular_noteRequest);
    void deleteNoteById(Integer noteId);

}
