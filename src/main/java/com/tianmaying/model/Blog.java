package com.tianmaying.model;

import java.util.Date;

public class Blog {
    private Long id;
    private String title;
    private String content;
    private Date createdTime;
    private User author;

    public Blog() {
        createdTime = new Date();
    }

    public Blog(String title, String content) {
        this();
        this.title = title;
        this.content = content;
    }

    public Blog(String title, String content, User author) {
        this(title, content);
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}