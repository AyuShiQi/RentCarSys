package com.ysq.web;

import com.ysq.pojo.Order;
import com.ysq.service.OrderServe;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/payOrder")
public class PayOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest require, HttpServletResponse response) throws ServletException, IOException {
        String userName = require.getParameter("userName");
        String vehicleId = require.getParameter("vehicleId");
        String rents = require.getParameter("rents");

        OrderServe.payOrder(userName,vehicleId,rents);
    }
}
