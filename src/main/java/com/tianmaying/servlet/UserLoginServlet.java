package com.tianmaying.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianmaying.model.User;
import com.tianmaying.service.UserService;
import com.tianmaying.utils.StringUtils;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private UserService userService;
    

    private boolean check(String cookie) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        String cookieValue = StringUtils.decodeByBase64(cookie);
        String cookieValues[] = cookieValue.split("\\|");
        if (cookieValues.length != 2) {
            return false;
        }

        User user = userService.getByUsername(cookieValues[0]);

        if (user == null) {
            return false;
        }

        String token = StringUtils.encodeByMd5(user.getUsername()
                + user.getEmail() + user.getPassword());

        if (!token.equals(cookieValues[1])) {
            return false;
        }

        return true;
    }
    
    private void perfromLogin(HttpServletRequest request,
            HttpServletResponse response, User user) throws IOException {
        String[] values = request.getParameterValues("remember-me");
        if (values != null && !values[0].isEmpty()) {// 这里表示用户勾选了Remember Me
            try {
                String cookie = StringUtils.encodeByMd5(user
                        .getUsername()
                        + user.getEmail()
                        + user.getPassword());
                String cookieValue = StringUtils.encodeByBase64(user
                        .getUsername() + "|" + cookie);
                Cookie c = new Cookie("user", cookieValue);
                c.setMaxAge(7 * 24 * 60 * 60);
                response.addCookie(c);
            } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        request.getSession().setAttribute("currentUser", user);
        
        String next = request.getParameter("next");
        if (next == null || next.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/blogs");
        } else {
            response.sendRedirect(next);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/WEB-INF/jsp/login.jsp");

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie c = cookies[i];
                if (c.getName().equals("user")) {
                    String userString = c.getValue();
                    try {
                        if (check(userString)) {
                            response.sendRedirect(request.getContextPath()
                                    + "/blogs");
                            return;
                        }
                    } catch (NoSuchAlgorithmException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String message = null;

        if (username == null || username.trim().length() == 0) {
            message = "用户名不能为空";
        } else {
            User user = userService.getByUsername(username);
            if (user == null) {
                message = "该用户不存在";
            } else {
                if (password == null || !password.equals(user.getPassword())) {
                    message = "密码不正确";
                } else {
                    perfromLogin(request, response, user);
                    return;
                }
            }
        }

        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        dispatcher.forward(request, response);
    }

}
