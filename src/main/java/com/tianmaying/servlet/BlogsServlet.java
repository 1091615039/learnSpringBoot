package com.tianmaying.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianmaying.model.Blog;
import com.tianmaying.service.BlogService;
import com.tianmaying.utils.Pager;

@WebServlet({"/blogs", ""})
public class BlogsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private BlogService blogService;
    
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        int page = 1;
        int size = 5; // 默认显示5条记录

        String pageString = request.getParameter("page");
        if (pageString != null) {
            try {
                page = Integer.parseInt(pageString);
            } catch(NumberFormatException e) {
                e.printStackTrace();
            }
        }

        Pager<Blog> blogPager = blogService.getAll(page, size);

        request.setAttribute("blogs", blogPager.getContent());
        request.setAttribute("pager", blogPager);
        RequestDispatcher dispatcher = request
                .getRequestDispatcher("/WEB-INF/jsp/list.jsp");
        dispatcher.forward(request, response);
    }

}
