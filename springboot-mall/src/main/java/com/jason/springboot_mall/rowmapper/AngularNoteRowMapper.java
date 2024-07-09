package com.jason.springboot_mall.rowmapper;

import com.jason.springboot_mall.model.AngularNote;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AngularNoteRowMapper implements RowMapper<AngularNote> {

    @Override
    public AngularNote mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        AngularNote angular_note=new AngularNote();
        angular_note.setNote_id(resultSet.getInt("note_id"));
        angular_note.setTitle(resultSet.getString("title"));
        angular_note.setDescription(resultSet.getString("description"));
        angular_note.setCreatedDate(resultSet.getTimestamp("created_date"));
        angular_note.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));
        return angular_note;
    }
}
