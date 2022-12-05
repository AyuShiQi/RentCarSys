package com.ysq.web;

import com.ysq.pojo.User;
import com.ysq.service.LoginServe;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/userLogin")
public class LoginServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest require, HttpServletResponse response) throws ServletException, IOException {
        String userName = require.getParameter("userName");
        String password = require.getParameter("password");

        User user = LoginServe.login(userName,password);
        System.out.println("ok");

        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        if(user!=null) {
            response.setStatus(200);
            out.write(user.parseToJSON());
        }
        else response.setStatus(403);
    }
}