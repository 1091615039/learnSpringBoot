package com.tianmaying.servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tianmaying.model.User;
import com.tianmaying.service.UserService;

@WebServlet("/register")
public class UserRegisterServlet extends HttpServlet  {
    private static final long serialVersionUID = 1L;
    
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
        dispatcher.forward(request, response);
    } 
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (email == null || !isEmail(email) || username == null || username.length() < 6 ||
                password == null || password.length() < 8) {
            request.setAttribute("message", "输入信息有误");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
            dispatcher.forward(request, response);
            
        } else {
            User user = new User(email, username, password);
            userService.addUser(user);
            request.getSession().setAttribute("currentUser", user);
            response.sendRedirect(request.getContextPath() + "/blogs");
        }
        
    }

    public static boolean isEmail(String email) {
        final String patternString = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        final Pattern pattern = Pattern.compile(patternString);
        final Matcher mat = pattern.matcher(email);
        return mat.find();
    }
    
}
