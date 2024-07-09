package com.jason.springboot_mall.dao.impl;

import com.jason.springboot_mall.dao.AngularToDoNoteDao;
import com.jason.springboot_mall.dto.AngularNoteRequest;
import com.jason.springboot_mall.model.AngularNote;
import com.jason.springboot_mall.rowmapper.AngularNoteRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AngularToDoNoteDaoImpl implements AngularToDoNoteDao
{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public Integer countNote(AngularNoteRequest angular_noteRequest) {
        return null;
    }

    @Override
    public List<AngularNote> getNotes()
    {
        String sql="SELECT  note_id, title,  " +
                "description, created_date, " +
                "last_modified_date " +
                "FROM Angular_ToDo_note WHERE 1=1 ";

        Map<String, Object>map=new HashMap<>();

        List<AngularNote> noteList = namedParameterJdbcTemplate.query(sql, map,
                new AngularNoteRowMapper());

        return noteList;
    }


    @Override
    public AngularNote getNoteById(Integer noteId) {
        String sql="SELECT  note_id, title, description, " +
                "created_date, last_modified_date " +
                " FROM Angular_ToDo_note WHERE note_id=:noteId ";

        Map<String, Object>map=new HashMap<>();
        map.put("noteId", noteId);
        List<AngularNote> noteList = namedParameterJdbcTemplate.query(sql, map,
                new AngularNoteRowMapper());

        if(!noteList.isEmpty())
        {
            return noteList.get(0);
        }
        else
        {
            return null;
        }
    }


    @Override
    public AngularNote getNoteByTitle(String noteTitle) {
        String sql="SELECT  note_id, title, description, " +
                "created_date, last_modified_date " +
                " FROM Angular_ToDo_note WHERE title=:noteTitle ";

        Map<String, Object>map=new HashMap<>();
        map.put("noteTitle", noteTitle);
        List<AngularNote> noteList = namedParameterJdbcTemplate.query(sql, map,
                new AngularNoteRowMapper());

        if(!noteList.isEmpty())
        {
            return noteList.get(0);
        }
        else
        {
            return null;
        }
    }

    @Override
    public Integer createNote(AngularNoteRequest angular_noteRequest) {
        String sql="INSERT INTO  Angular_ToDo_note( title, description, " +
                " created_date, last_modified_date )" +
                "VALUES (:title, :description,  "+
                ":createDate, :lastModifiedDate) ";

        Map<String, Object> map=new HashMap<>();
        map.put("title", angular_noteRequest.getTitle());
        map.put("description", angular_noteRequest.getDescription());

        Date now=new Date();
        map.put("createDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder=new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
        int noteId=keyHolder.getKey().intValue();

        return noteId;
    }

    @Override
    public void updateNote(Integer noteId, AngularNoteRequest angular_noteRequest) {

        String sql="UPDATE Angular_ToDo_note SET  " +
                "title= :title," +
                " description= :description, " +
                "last_modified_date= :lastModifiedDate " +
                "WHERE note_id= :noteId ";

        Map<String, Object>map=new HashMap<>();
        map.put("noteId", noteId);
        map.put("title", angular_noteRequest.getTitle());
        map.put("description", angular_noteRequest.getDescription());

        Date now=new Date();

        map.put("lastModifiedDate", now);
        namedParameterJdbcTemplate.update(sql, map);


    }

    @Override
    public void deleteNoteById(Integer noteId) {
        String sql="DELETE FROM Angular_ToDo_note " +
                "WHERE note_id= :noteId ";
        Map<String, Object>map=new HashMap<>();
        map.put("noteId", noteId);
        namedParameterJdbcTemplate.update(sql, map);
    }
}
