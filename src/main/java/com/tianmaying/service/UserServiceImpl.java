package com.tianmaying.service;

import com.tianmaying.model.User;
import com.tianmaying.model.UserRepository;

public class UserServiceImpl implements UserService {
    
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getByUsername(String userName) {
        return userRepository.getByUsername(userName);
    }

    @Override
    public void addUser(User user) {
        user.setAvatar("avatar");
        user.setDescription("天码营秉承让技术学习更加高效和便捷的使命，致力于打造新一代的技术学习服务平台，提供创新并且专业的内容、工具与服务，帮助学习者与从业者实现个人价值。");
        userRepository.add(user);
    }

}
