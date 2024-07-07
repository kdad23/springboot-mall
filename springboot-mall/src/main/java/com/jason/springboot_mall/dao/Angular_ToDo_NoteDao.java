package com.jason.springboot_mall.dao;

import com.jason.springboot_mall.dto.Angular_NoteRequest;
import com.jason.springboot_mall.dto.ProductQueryParams;
import com.jason.springboot_mall.dto.ProductRequest;
import com.jason.springboot_mall.model.Angular_Note;
import com.jason.springboot_mall.model.Product;

import java.util.List;

public interface Angular_ToDo_NoteDao {

    Integer countNote(Angular_NoteRequest angular_noteRequest);

    List<Angular_Note> getNotes( );
    Angular_Note getNoteById(Integer noteId);
    Angular_Note getNoteByTitle(String noteTitle);
    Integer createNote(Angular_NoteRequest angular_noteRequest);

    void updateNote(Integer noteId, Angular_NoteRequest angular_noteRequest);
    void deleteNoteById(Integer noteId);
}
