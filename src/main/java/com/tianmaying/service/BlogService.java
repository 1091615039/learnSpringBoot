package com.tianmaying.service;

import com.tianmaying.model.Blog;
import com.tianmaying.utils.Pager;


public interface BlogService {
    
    public Pager<Blog> getAll(int page, int size);

}