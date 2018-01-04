package com.tianmaying;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.tianmaying.model.UserRepository;
import com.tianmaying.service.UserService;
import com.tianmaying.service.UserServiceImpl;

@EnableAspectJAutoProxy
@SpringBootApplication
@ServletComponentScan
@Configuration
public class App {
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
    
    @Autowired
    private UserRepository userRepository;
    
    @Bean
    public UserService userServiceBean() {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserRepository(userRepository);
        return userService;
    }
    
}