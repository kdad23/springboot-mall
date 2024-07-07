package com.jason.springboot_mall.rowmapper;

import com.jason.springboot_mall.model.Angular_ToDO_User;
import com.jason.springboot_mall.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Angular_UserRowMappper implements RowMapper<Angular_ToDO_User> {

    @Override
    public Angular_ToDO_User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Angular_ToDO_User angular_toDO_user=new Angular_ToDO_User();
        angular_toDO_user.setUserId(resultSet.getInt("user_id"));
        angular_toDO_user.setEmail(resultSet.getString("email"));
        angular_toDO_user.setPassword(resultSet.getString("password"));
        angular_toDO_user.setCreatedDate(resultSet.getTimestamp("created_date"));
        angular_toDO_user.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));
        angular_toDO_user.setRoles(resultSet.getString("roles"));
        angular_toDO_user.setAccessToken(resultSet.getString("accessToken"));
        return angular_toDO_user;
    }
}
