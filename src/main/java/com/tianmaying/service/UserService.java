package com.tianmaying.service;

import com.tianmaying.model.User;

public interface UserService {
    
    User getByUsername(String userName);
    void addUser(User user);

}
