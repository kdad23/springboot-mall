package com.jason.springboot_mall.service.impl;

import com.jason.springboot_mall.dao.AngularToDoNoteDao;
import com.jason.springboot_mall.dto.AngularNoteRequest;
import com.jason.springboot_mall.model.AngularNote;
import com.jason.springboot_mall.service.AngularToDoNoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AngularToDoNoteServiceImpl implements AngularToDoNoteService {

    private final static Logger log= LoggerFactory.getLogger(AngularToDoNoteServiceImpl.class);
    @Autowired
    private AngularToDoNoteDao angular_toDo_noteDao;
    @Override
    public Integer countNote(AngularNoteRequest angular_noteRequest) {
        return null;
    }

    @Override
    public List<AngularNote> getNotes( ) {
        return angular_toDo_noteDao.getNotes();
    }

    @Override
    public AngularNote getNoteById(Integer noteId) {
        return angular_toDo_noteDao.getNoteById(noteId);
    }

    @Override
    public AngularNote getNoteByTitle(String noteTitle) {
        return angular_toDo_noteDao.getNoteByTitle(noteTitle);
    }

    @Override
    public Integer createNote(AngularNoteRequest angular_noteRequest) {
        return angular_toDo_noteDao.createNote(angular_noteRequest);
    }
    @Override
    public void updateNote(Integer noteId, AngularNoteRequest angular_noteRequest) {
        angular_toDo_noteDao.updateNote(noteId, angular_noteRequest);
    }
    @Override
    public void deleteNoteById(Integer noteId) {
        angular_toDo_noteDao.deleteNoteById(noteId);
    }
}
