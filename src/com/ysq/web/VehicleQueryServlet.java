package com.ysq.web;

import com.ysq.service.VehicleServe;
import com.ysq.utils.StringUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getCar")
public class VehicleQueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest require, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter out = response.getWriter();

        // 获取车牌号
        String vehicleId = require.getParameter("vehicleId");
        if(vehicleId!=null) {
            String infos = VehicleServe.getCarWithVehicleId(vehicleId);

            response.setStatus(200);
            out.write(StringUtils.ObjectToJOSN(infos));
        }
        else {
            // 获取类型
            String type = require.getParameter("type");
            // 获取品牌
            String brand = require.getParameter("brand");

            // brand信息为空
            if(brand==null) {
                // type获取，还要返回brands信息
                String infos = VehicleServe.getCarWithType(type);
                // 获取brands信息
                String brands = VehicleServe.getBrands(type);

                response.setStatus(200);
                out.write(StringUtils.ObjectToJOSN(infos,brands));
            }
            // 不为空通过brand获取
            else {
                // 获取型号
                String model = require.getParameter("model");
                // model为空
                if(model==null) {
                    String infos = VehicleServe.getCarWithBrand(type,brand);
                    String models = VehicleServe.getModels(type,brand);

                    response.setStatus(200);
                    out.write(StringUtils.ObjectToJOSN(infos,models));
                }
                else {
                    String infos = VehicleServe.getCarWithModel(type,brand,model);

                    response.setStatus(200);
                    out.write(StringUtils.ObjectToJOSN(infos));
                }
            }
        }
    }
}
