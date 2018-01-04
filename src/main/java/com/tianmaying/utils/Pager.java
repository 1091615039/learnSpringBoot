package com.tianmaying.utils;

import java.util.List;

public class Pager<T> {
    
    private boolean hasPrevious;
    private boolean hasNext;
    private int page;
    private int size;
    private int total;
    private List<T> content;
    
    public Pager(int page, int size, int total, List<T> content) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.content = content;
        
        if (page > 1) {
            hasPrevious = true;
        }
        
        if (page * size < total) {
            hasNext = true;
        }
    }
    
    public boolean isHasPrevious() {
        return hasPrevious;
    }
    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }
    public boolean isHasNext() {
        return hasNext;
    }
    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public List<T> getContent() {
        return content;
    }
    public void setContent(List<T> content) {
        this.content = content;
    }
    
}
