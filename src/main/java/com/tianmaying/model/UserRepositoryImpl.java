package com.tianmaying.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianmaying.utils.DBHelper;

@Component
public class UserRepositoryImpl implements UserRepository {
    
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public User getByUserId(Long userId) {
        return (User)DBHelper.queryObject("select * from user where id=" + userId, userMapper);
    }
    
    @Override
    public List<User> getAll() {
        return DBHelper.queryList("select * from `user`", userMapper, User.class);
    }
    
    @Override
    public User getByUsername(String username) {
        return (User)DBHelper.queryObject(String.format("select * from user where username='%s'", username), userMapper);
    }
    
    @Override
    public void add(User user) {
        String sql = String.format("insert into user(username, password, email, description, avatar) values ('%s', '%s', '%s', '%s', '%s') ",
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getDescription(),
                user.getAvatar());
        long id = DBHelper.create(sql);
        user.setId(id);
    }

}