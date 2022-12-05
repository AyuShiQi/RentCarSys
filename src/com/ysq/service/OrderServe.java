package com.ysq.service;

import com.ysq.dao.OrderDAO;
import com.ysq.dao.VehicleDAO;
import com.ysq.utils.StringUtils;

public class OrderServe {
    private OrderServe(){}

    public static boolean addOrder(String userName,String vehicleId,double rents) {
        return OrderDAO.setOrder(userName,vehicleId,rents);
    }

    public static boolean payOrder(String userName,String vehicleId,String rents) {
        double rent = Double.parseDouble(rents);
        return OrderDAO.updateOrder(userName,vehicleId,rent);
    }

    public static String getOrder(String userName) {
        return "\"infos\":"+OrderDAO.getOrder(userName);
    }

    public static String getOrderInfos() {
        String allCount = getAllCount();
        String noPayCount = getNoPayCount();
        String payedCount = getPayedCount();
        String allRent = getAllRent();
        return StringUtils.ObjectToJOSN(allCount,noPayCount,payedCount,allRent);
    }

    public static String getAllCount() {
        return "\"allCount\":"+OrderDAO.getAllCount();
    }

    public static String getNoPayCount() {
        return "\"noPayCount\": "+OrderDAO.getNoPayCount();
    }

    public static String getPayedCount() {
        return "\"payedCount\": "+OrderDAO.getPayedCount();
    }

    public static String getAllRent() {
        return "\"allRent\": "+OrderDAO.getAllRent();
    }
}
