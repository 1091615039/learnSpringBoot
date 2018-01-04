package com.tianmaying.model;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianmaying.utils.DBHelper;

@Component
public class BlogRepositoryImpl implements BlogRepository {
    
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public int countAll() {
        return DBHelper.queryCount("select count(*) from blog");
    }

    @Override
    public List<Blog> getAll(int page, int size) {
        String sql = String.format("select * from blog limit %d, %d", (page - 1) * size, size);
        return DBHelper.queryList(sql, blogMapper, Blog.class);
    }
    
    
    public List<Blog> getAll() {
        return DBHelper.queryList("select * from blog", blogMapper, Blog.class);
    }
    
    @Override
    public Blog getBlogById(long id) {
        return (Blog)DBHelper.queryObject("select * from blog where id=" + id, blogMapper);
    }
    
    @Override
    public List<Blog> getBlogsByUserId(long userId) {
        return DBHelper.queryList("select * from blog where author=" + userId, blogMapper, Blog.class);
    }

    @Override
    public void add(Blog blog) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        String sql = String.format("insert blog(createdTime, author, title, content) values ('%s', %s, '%s', '%s') ", 
                sdf.format(blog.getCreatedTime()),
                blog.getAuthor().getId(),
                blog.getTitle(),
                blog.getContent());
        
        long id = DBHelper.create(sql);
        blog.setId(id);
    }

    @Override
    public void remove(long id) {
        DBHelper.update("delete from blog where id=" + id);
    }
}

