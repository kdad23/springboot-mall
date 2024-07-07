package com.jason.springboot_mall.rowmapper;

import com.jason.springboot_mall.constant.ProductCategory;
import com.jason.springboot_mall.model.Angular_Note;
import com.jason.springboot_mall.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Angular_Note_RowMapper implements RowMapper<Angular_Note> {

    @Override
    public Angular_Note mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Angular_Note angular_note=new Angular_Note();
        angular_note.setNote_id(resultSet.getInt("note_id"));
        angular_note.setTitle(resultSet.getString("title"));
        angular_note.setDescription(resultSet.getString("description"));
        angular_note.setCreatedDate(resultSet.getTimestamp("created_date"));
        angular_note.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));
        return angular_note;
    }
}
