package com.ysq.service;

import com.ysq.pojo.Vehicle;
import com.ysq.dao.VehicleDAO;
import com.ysq.utils.StringUtils;

public class VehicleServe {
    /**
     * 通过车牌号去搜索汽车
     * @param vehicleId
     * @return
     */
    public static String getCarWithVehicleId(String vehicleId) {
        Vehicle[] vehicles = VehicleDAO.queryVehicleWithVehicleId(vehicleId);
        return Vehicle.parseListJSON(vehicles);
    }

    /**
     * 通过类型获得汽车信息
     * @param type 类型
     * @return 汽车数组
     */
    public static String getCarWithType(String type) {
        Vehicle[] vehicles = VehicleDAO.queryVehicleWithType(type);
        System.out.println(Vehicle.parseListJSON(vehicles));
        return Vehicle.parseListJSON(vehicles);
    }

    public static String getCarWithBrand(String type, String brand) {
        Vehicle[] vehicles = VehicleDAO.queryVehicleWithBrand(type,brand);
        System.out.println(Vehicle.parseListJSON(vehicles));
        return Vehicle.parseListJSON(vehicles);
    }

    public static String getCarWithModel(String type, String brand,String model) {
        Vehicle[] vehicles = VehicleDAO.queryVehicleWithModel(type,brand,model);
        System.out.println(Vehicle.parseListJSON(vehicles));
        return Vehicle.parseListJSON(vehicles);
    }

    /**
     * 通过类型获取品牌
     * @param type 类型
     * @return 品牌数组
     */
    public static String getBrands(String type) {
        String[] str = VehicleDAO.queryBrands(type);
        System.out.println(StringUtils.parseArrayJSON(str));
        return "\"brands\":" + StringUtils.parseArrayJSON(str);
    }

    /**
     *
     * @param type
     * @return
     */
    public static String getModels(String type,String brand) {
        String[] str = VehicleDAO.queryModels(type,brand);
        System.out.println(StringUtils.parseArrayJSON(str));
        return "\"models\":" + StringUtils.parseArrayJSON(str);
    }

    public static boolean deleteVehicle(String vehicleId) {
        return VehicleDAO.deleteVehicleWithId(vehicleId);
    }

    public static boolean updateVehicle(String vehicleId, String type,String brand, String model, String perRent) {
        if(brand.equals("")) brand = "无品牌";
        if(model.equals("")) model = "无型号";
        if(perRent.equals("NaN")) perRent = "0";

        int rent = Integer.parseInt(perRent);

        return VehicleDAO.updateVehicle(vehicleId,type,brand,model,rent);
    }

    public static boolean addVehicle(String vehicleId, String type,String brand, String model, String perRent) {
        // 车牌为无禁止添加
        if(vehicleId.equals("")||vehicleId.length()>8) return false;
        if(!Vehicle.isLegal(type)) return false;
        if(brand.equals("")) brand = "无品牌";
        if(model.equals("")) model = "无型号";
        if(perRent.equals("NaN")) perRent = "0";
        int rent = Integer.parseInt(perRent);

        return VehicleDAO.addVehicle(vehicleId,type,brand,model,rent);
    }

    public static float rentVehicle(String vehicleId, String days) {
        int day = Integer.parseInt(days);
        Vehicle vehicle = VehicleDAO.queryVehicleWithVehicleId(vehicleId)[0];

        return vehicle.calRent(day);
    }
}
