package com.jason.springboot_mall.service;

import com.jason.springboot_mall.dto.*;
import com.jason.springboot_mall.model.Angular_Note;

import java.util.List;

public interface Angular_ToDo_NoteService {

    Integer countNote(Angular_NoteRequest angular_noteRequest);
    List<Angular_Note> getNotes( );
    Angular_Note getNoteById(Integer noteId);
    Angular_Note getNoteByTitle(String noteTitle);
    Integer createNote(Angular_NoteRequest angular_noteRequest);
    void updateNote(Integer noteId, Angular_NoteRequest angular_noteRequest);
    void deleteNoteById(Integer noteId);

}
