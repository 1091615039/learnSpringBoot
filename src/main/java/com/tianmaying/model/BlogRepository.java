package com.tianmaying.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianmaying.utils.RowMapper;

@Component
class BlogMapper implements RowMapper {
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public Object map(ResultSet resultSet) throws SQLException {
        Blog blog = new Blog();
        blog.setId(resultSet.getLong("id"));
        blog.setCreatedTime(resultSet.getDate("createdTime"));
        blog.setTitle(resultSet.getString("title"));
        blog.setContent(resultSet.getString("content"));
        
        User user = userRepository.getByUserId(resultSet.getLong("author"));
        blog.setAuthor(user);
        
        return blog;
    }
}

public interface BlogRepository {
    
    int countAll();
    List<Blog> getAll(int page, int size);
    Blog getBlogById(long id);
    List<Blog> getBlogsByUserId(long userId);
    void add(Blog blog);
    void remove(long id);
}

