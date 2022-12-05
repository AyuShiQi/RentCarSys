package com.ysq.web;

import com.ysq.service.OrderServe;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getOrderInfos")
public class GetOrderInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest require, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();
        out.write(OrderServe.getOrderInfos());
    }
}
