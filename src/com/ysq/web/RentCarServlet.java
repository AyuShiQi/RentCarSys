package com.ysq.web;

import com.ysq.service.VehicleServe;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/rentCar")
public class RentCarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest require, HttpServletResponse response) throws ServletException, IOException {
        String userName = require.getParameter("userName");
        String vehicleId = require.getParameter("vehicleId");
        String days = require.getParameter("days");

        double rent = VehicleServe.rentVehicle(userName,vehicleId,days);
        response.setStatus(200);
        PrintWriter out = response.getWriter();
        out.write("{\"rent\":"+rent+" }");
    }
}
