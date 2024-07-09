package com.jason.springboot_mall.rowmapper;

import com.jason.springboot_mall.model.AngularToDoUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AngularUserRowMappper implements RowMapper<AngularToDoUser> {

    @Override
    public AngularToDoUser mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        AngularToDoUser angular_toDo_user =new AngularToDoUser();
        angular_toDo_user.setUserId(resultSet.getInt("user_id"));
        angular_toDo_user.setEmail(resultSet.getString("email"));
        angular_toDo_user.setPassword(resultSet.getString("password"));
        angular_toDo_user.setCreatedDate(resultSet.getTimestamp("created_date"));
        angular_toDo_user.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));
        angular_toDo_user.setRoles(resultSet.getString("roles"));
        angular_toDo_user.setAccessToken(resultSet.getString("accessToken"));
        return angular_toDo_user;
    }
}
