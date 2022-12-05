package com.ysq.web;

import com.ysq.pojo.Vehicle;
import com.ysq.service.VehicleServe;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/updateCar")
public class updateCarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest require, HttpServletResponse response) throws ServletException, IOException {
        String vehicleId = require.getParameter("vehicleId");
        String type = require.getParameter("type");
        String brand = require.getParameter("brand");
        String model = require.getParameter("model");
        String perRent = require.getParameter("perRent");
        // 通过vehicleId去改变
        if(VehicleServe.updateVehicle(vehicleId,type,brand,model,perRent)) {
            response.setStatus(200);
        }
        else response.setStatus(403);
    }
}
