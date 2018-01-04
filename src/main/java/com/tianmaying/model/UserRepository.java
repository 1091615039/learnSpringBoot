package com.tianmaying.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tianmaying.utils.RowMapper;

@Component
class UserMapper implements RowMapper {

    @Override
    public Object map(ResultSet resultSet) throws SQLException {

        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setDescription(resultSet.getString("description"));
        return user;
    }
}

public interface UserRepository {
    List<User> getAll();
    User getByUserId(Long userId);
    User getByUsername(String username);
    void add(User user);
}