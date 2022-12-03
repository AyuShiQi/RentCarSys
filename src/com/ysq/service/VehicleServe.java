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
    public static String getModels(String type) {
        return null;
    }
}
