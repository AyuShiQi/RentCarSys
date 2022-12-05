package com.ysq.web;

import com.ysq.service.OrderServe;
import com.ysq.utils.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getOrder")
public class OrderCarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest require, HttpServletResponse response) throws ServletException, IOException {
        String userName = require.getParameter("userName");

        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        response.setStatus(200);

        out.write(StringUtils.ObjectToJOSN(OrderServe.getOrder(userName)));
    }
}
