package com.ysq.web;

import com.ysq.pojo.User;
import com.ysq.service.LoginPart;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@WebServlet("/userLogin")
public class LoginServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest require, HttpServletResponse response) throws ServletException, IOException {
        Map<String,String[]> r = require.getParameterMap();
        System.out.println(r);
        String userName = require.getParameter("userName");
        String passWord = require.getParameter("passWord");

        User user = LoginPart.login(userName,passWord);
        System.out.println("ok");

        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        out.write(user.parseToJSON());
    }
}