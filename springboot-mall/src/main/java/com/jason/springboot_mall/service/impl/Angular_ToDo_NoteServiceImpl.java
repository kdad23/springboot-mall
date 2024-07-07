package com.jason.springboot_mall.service.impl;

import com.jason.springboot_mall.dao.Angular_ToDo_NoteDao;
import com.jason.springboot_mall.dto.Angular_NoteRequest;
import com.jason.springboot_mall.model.Angular_Note;
import com.jason.springboot_mall.service.Angular_ToDo_NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Angular_ToDo_NoteServiceImpl implements Angular_ToDo_NoteService {

    private final static Logger log= LoggerFactory.getLogger(Angular_ToDo_NoteServiceImpl.class);
    @Autowired
    private Angular_ToDo_NoteDao angular_toDo_noteDao;
    @Override
    public Integer countNote(Angular_NoteRequest angular_noteRequest) {
        return null;
    }

    @Override
    public List<Angular_Note> getNotes( ) {
        return angular_toDo_noteDao.getNotes();
    }

    @Override
    public Angular_Note getNoteById(Integer noteId) {
        return angular_toDo_noteDao.getNoteById(noteId);
    }

    @Override
    public Angular_Note getNoteByTitle(String noteTitle) {
        return angular_toDo_noteDao.getNoteByTitle(noteTitle);
    }

    @Override
    public Integer createNote(Angular_NoteRequest angular_noteRequest) {
        return angular_toDo_noteDao.createNote(angular_noteRequest);
    }
    @Override
    public void updateNote(Integer noteId, Angular_NoteRequest angular_noteRequest) {
        angular_toDo_noteDao.updateNote(noteId, angular_noteRequest);
    }
    @Override
    public void deleteNoteById(Integer noteId) {
        angular_toDo_noteDao.deleteNoteById(noteId);
    }
}
