package com.ysq.web;

import com.ysq.service.VehicleServe;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteCar")
public class DeleteCarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest require, HttpServletResponse response) throws ServletException, IOException {
        // 获取车牌号
        String vehicleId = require.getParameter("vehicleId");
        System.out.println(vehicleId);
        if(VehicleServe.deleteVehicle(vehicleId)) {
            response.setStatus(200);
        }
    }
}
