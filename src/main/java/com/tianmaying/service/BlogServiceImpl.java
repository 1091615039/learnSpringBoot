package com.tianmaying.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianmaying.model.Blog;
import com.tianmaying.model.BlogRepository;
import com.tianmaying.utils.Pager;

@Service
public class BlogServiceImpl implements BlogService {
    
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Pager<Blog> getAll(int page, int size) {
        List<Blog> blogs = blogRepository.getAll(page, size);
        int blogsLength = blogRepository.countAll();
        return new Pager<>(page, size, blogsLength, blogs);
    }

}