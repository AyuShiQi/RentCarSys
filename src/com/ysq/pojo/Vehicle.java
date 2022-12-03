package com.ysq.pojo;

import com.ysq.dao.VehicleDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public abstract class Vehicle {
    /**
     * 车牌号
     */
    private String vehicleId;
    /**
     * 品牌
     */
    private String brand;
    /**
     * 日租金
     */
    private int perRent;

    public Vehicle() {
    }

    /**
     * 返回数据库对应表名
     * @param type 型号
     * @return 表名
     */
    public static String getTypeName(String type){
        if(type.equals("轿车")) return "car";
        if(type.equals("货车")) return "truck";
        if(type.equals("客车")) return "bus";
        return "";
    }
    /**
     * 通过数据创建汽车数组
     * @param rs 结果集
     * @return 汽车数组
     * @throws SQLException 数据集的异常
     */

    /**
     * 将汽车数组转化成JSON字符串
     * @return
     */
    public static String parseListJSON(Vehicle[] vehicles) {
        StringBuilder str = new StringBuilder();
        str.append("\"infos\": [");
        boolean begin = true;

        for(Vehicle vehicle : vehicles) {
            if (!begin) str.append(',');
            else begin = !begin;

            str.append(vehicle.parseJSON());
        }
        str.append(']');
        return new String(str);
    }

    public static Vehicle[] createVehicles(ResultSet rs) throws SQLException {
        List<Vehicle> list = new LinkedList<>();
        System.out.println(rs.getRow());

        while (rs.next()) {
            String vehicleId = rs.getString("vehicleId");
            String type = rs.getString("type_name");
            String brand = rs.getString("brand");
            int perRent = rs.getInt("perRent");

            if(type.equals("轿车")) {
                String model = rs.getString("model");
                list.add(new Car(vehicleId,brand,model,perRent));
            }
            else if(type.equals("客车")) {
                int seats = rs.getInt("model");
                list.add(new Bus(vehicleId,brand,perRent,seats));
            }
            else {
                int weigth = rs.getInt("model");
                list.add(new Truck(vehicleId,brand,perRent,weigth));
            }
        }
        return list.toArray(new Vehicle[list.size()]);
    }


    /**
     * 创建Vehicle
     *
     * @param vehicleId 车牌号
     * @param brand     品牌名
     * @param perRent   日租金
     */
    public Vehicle(String vehicleId, String brand, int perRent) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.perRent = perRent;
    }

    /**
     * 获取汽车类型的中文名字
     *
     * @return 汽车类型中文名
     */
    public abstract String getName();

    /**
     * 计算租金
     *
     * @param days 租车天数
     * @return 租金
     */
    public abstract float calRent(int days);

    /**
     * 租车流程
     */
    public void leaseOutFlow() {

    }

    /**
     * 以车牌号作为车的唯一标识
     *
     * @param obj 另一对象
     * @return 返回判别结果
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof Vehicle) {
            return ((Vehicle) obj).vehicleId.equals(this.vehicleId);
        }
        return false;
    }

    /**
     * 获取车牌号
     *
     * @return 车牌号
     */
    public String getVehicleId() {
        return vehicleId;
    }

    /**
     * 设置车牌号
     *
     * @param vehicleId 新车牌号
     */
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * 获取汽车品牌
     *
     * @return 汽车品牌
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 设置汽车品牌
     *
     * @param brand 新汽车品牌
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * 设置日租金
     *
     * @param perRent 新的日租金
     * @return 是否更新成功
     */
    public boolean setPerRent(int perRent) {
        if (perRent <= 0) return false;
        this.perRent = perRent;
        return true;
    }

    /**
     * 获取日租金
     *
     * @return 日租金
     */
    public int getPerRent() {
        return perRent;
    }

    /**
     * 获取不同类型汽车对于型号的中文名表示
     *
     * @return 型号名称
     */
    public abstract String getTypeName();

    /**
     * 获取型号的值
     *
     * @return 型号值
     */
    public abstract String getModel();

    /**
     * 汽车对象的String表示
     *
     * @return 表示字符串
     */
    @Override
    public String toString() {
        return getName() + vehicleId + "," + brand + "," + getModel() + "," + perRent;
    }

    public String parseJSON() {
        return "{" +
                "\"vehicleId\": \""+vehicleId + "\"," +
                "\"type\": \"" + getName() + "\"," +
                "\"brand\": \"" + brand + "\"," +
                "\"perRent\": " + perRent + "," +
                "\"model\": \"" + getModel() + "\"" +
                "}";
    }

    /**
     * 更新车辆信息
     */
    public void updateVehicle() {

    }

    /**
     * 更新日租金
     */
    public void updatePerRent() {

    }
    /**
     * 更新汽车型号信息
     */
    public abstract void updateModel();
}
