package com.jason.springboot_mall.service.impl;

import com.jason.springboot_mall.dao.AngularToDoNoteDao;
import com.jason.springboot_mall.dto.AngularNoteRequest;
import com.jason.springboot_mall.dto.AngularToDoDTO;
import com.jason.springboot_mall.model.AngularToDo;
import com.jason.springboot_mall.model.AngularToDoForJPA;
import com.jason.springboot_mall.dao.AngularToDoRepository;
import com.jason.springboot_mall.service.AngularToDoNoteService;
import com.jason.springboot_mall.util.BeanCopyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AngularToDoNoteServiceImpl implements AngularToDoNoteService {

    private final static Logger log= LoggerFactory.getLogger(AngularToDoNoteServiceImpl.class);

    @Autowired
    private AngularToDoRepository angularToDoRepository;

    @Autowired
    private AngularToDoNoteDao angular_toDo_noteDao;
    @Override
    public Integer countNote(AngularNoteRequest angular_noteRequest) {
        return null;
    }

    @Override
    public List<AngularToDo> getNotes( ) {
        return angular_toDo_noteDao.getNotes();
    }

    @Override
    public AngularToDo getNoteById(Integer noteId) {
        return angular_toDo_noteDao.getNoteById(noteId);
    }

    @Override
    public AngularToDo getNoteByTitle(String noteTitle) {
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

    ////////////////////////////////////////////////////////////
    // JPA用


    @Override
    public Integer createToDoByJPA(AngularNoteRequest angularNoteRequest)
    {
        AngularToDoForJPA angularToDoForJPA =new AngularToDoForJPA();
        BeanUtils.copyProperties(angularNoteRequest, angularToDoForJPA);
        angularToDoRepository.save(angularToDoForJPA);
        Integer toDoId=
                angularToDoRepository.findById(angularToDoForJPA.getNote_id()).orElse(null).getNote_id();

        return toDoId;
    }


    @Override
    public AngularToDoDTO getToDoByIdByJPA(Integer toDoId)
    {
        AngularToDoForJPA angularToDoForJPA=angularToDoRepository.findById(toDoId).orElse(null);
        AngularToDoDTO angularToDoDTO=new AngularToDoDTO();
        assert angularToDoForJPA != null;
        BeanUtils.copyProperties(angularToDoForJPA, angularToDoDTO);
        return angularToDoDTO;
    }


    @Override
    public List<AngularToDoDTO> getToDosByJPA()
    {
        List<AngularToDoForJPA> angularToDoForJPA=angularToDoRepository.findAll();
        List<AngularToDoDTO> angularToDoDTOList=BeanCopyUtils.copyListProperties(angularToDoForJPA, AngularToDoDTO::new);
         return angularToDoDTOList;
    }

    @Override
    public List<AngularToDoDTO> getToDoByTitle(String title)
    {
        List<AngularToDoForJPA> angularToDoForJPAList=angularToDoRepository.findByTitleLike(title);
        List<AngularToDoDTO> angularToDoDTOList=BeanCopyUtils.copyListProperties(angularToDoForJPAList, AngularToDoDTO::new);
        return angularToDoDTOList;
    }


    @Override
    public void deleteToDoById(Integer toDoId)
    {
        angularToDoRepository.deleteById(toDoId);
    }


    @Override
    public void updateToDoByJPA(Integer toDoId, AngularNoteRequest angularNoteRequest)
    {
        AngularToDoForJPA angularToDoForJPA =new AngularToDoForJPA();
        BeanUtils.copyProperties(angularNoteRequest, angularToDoForJPA);

        // 從資料庫裡取出的資料
        AngularToDoForJPA angularToDoForJPA2=
                angularToDoRepository.findById(angularToDoForJPA.getNote_id()).orElse(null);

        if (angularToDoForJPA2 != null)
        {
            angularToDoForJPA2.setLastModifiedDate(new Date());
            BeanUtils.copyProperties(angularNoteRequest, angularToDoForJPA2);
            angularToDoRepository.save(angularToDoForJPA2);
        }


    }
}
