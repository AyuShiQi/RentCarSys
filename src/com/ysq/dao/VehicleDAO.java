package com.ysq.dao;

import com.ysq.pojo.Vehicle;
import com.ysq.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleDAO {
    private VehicleDAO() {
    }
    /**
     * 通过车牌号获取车信息
     * @param vehicleId 车牌号
     * @return 车
     */
    public static Vehicle[] queryVehicleWithVehicleId(String vehicleId) {
        String sql = "select vehicleId,brand,perRent,type_name,model" +
                " from vehicle,vehicle_type,car" +
                " where vehicleId = vehicle_id" +
                "   and vehicleId = ?" +
                "  and type_id = type" +
                " union" +
                " select vehicleId,brand,perRent,type_name,model" +
                " from vehicle,vehicle_type,truck" +
                " where vehicleId = vehicle_id" +
                "   and vehicleId = ?" +
                "  and type_id = type" +
                " union" +
                " select vehicleId,brand,perRent,type_name,model" +
                " from vehicle,vehicle_type,bus" +
                " where vehicleId = vehicle_id" +
                "   and vehicleId = ?" +
                "  and type_id = type";

        Connection connection = DBUtils.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;

        try {
            p = connection.prepareStatement(sql);
            p.setString(1,vehicleId);
            p.setString(2,vehicleId);
            p.setString(3,vehicleId);

            rs = p.executeQuery();
            Vehicle[] vehicles = Vehicle.createVehicles(rs);
            return vehicles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection,p,rs);
        }
    }

    /**
     * 查询类型的车
     * @param type 类型
     * @return 车
     */
    public static Vehicle[] queryVehicleWithType(String type) {
        String sql = "select vehicleId,brand,perRent,type_name,model" +
                " from vehicle,vehicle_type,"+Vehicle.getTypeName(type) +
                " where type=type_id and vehicleId=vehicle_id";

        Connection connection = DBUtils.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;
        try {
            p = connection.prepareStatement(sql);
            rs = p.executeQuery();

            Vehicle[] vehicles = Vehicle.createVehicles(rs);
            return vehicles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection,p,rs);
        }
    }

    /**
     * 通过品牌获取汽车信息
     * @param type
     * @param brand
     * @return
     */
    public static Vehicle[] queryVehicleWithBrand(String type,String brand) {
        String sql = "select vehicleId,brand,perRent,type_name,model" +
                    " from vehicle,vehicle_type,"+Vehicle.getTypeName(type)+
                    " where type = type_id and brand = ? and vehicleId=vehicle_id;";
        Connection connection = DBUtils.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;

        try {
            p = connection.prepareStatement(sql);
            p.setString(1,brand);
            rs = p.executeQuery();
            return Vehicle.createVehicles(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection,p,rs);
        }
    }

    public static Vehicle[] queryVehicleWithModel(String type,String brand,String model) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;
        String sql = "select vehicleId,brand,perRent,type_name,model" +
                " from vehicle,vehicle_type,"+Vehicle.getTypeName(type)+
                " where type = type_id and brand = ? and model= ? and vehicleId=vehicle_id;";

        try {
            p = connection.prepareStatement(sql);

            p.setString(1,brand);
            p.setString(2,model);

            rs = p.executeQuery();
            return Vehicle.createVehicles(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection,p,rs);
        }
    }

    /**
     *
     * @param type
     * @return
     */
    public static String[] queryBrands(String type) {
        String sql = "select distinct brand from vehicle" +
                " where type = (select type_id from vehicle_type where type_name=?)";

        Connection connection = DBUtils.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;

        try {
            p = connection.prepareStatement(sql);
            p.setString(1,type);

            rs = p.executeQuery();
            return DBUtils.parseToString(rs,"brand");
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public static String[] queryModels(String type,String brand) {
        String sql = "select distinct model from vehicle,"+Vehicle.getTypeName(type)+
                " where brand = ? and vehicleId = vehicle_id;";

        Connection connection = DBUtils.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;

        try {
            p = connection.prepareStatement(sql);
            p.setString(1,brand);

            rs = p.executeQuery();
            return DBUtils.parseToString(rs,"model");
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteVehicleWithId(String vehicleId) {
        // 先查询车辆类型
        String type = null;
        String sql1 = "select type_name from vehicle,vehicle_type where type=type_id and vehicleId = ?";
        String sql2 = "delete from vehicle where vehicleId = ?";

        Connection connection = DBUtils.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;

        try {
            p = connection.prepareStatement(sql1);
            p.setString(1,vehicleId);

            rs = p.executeQuery();
            type = (DBUtils.parseToString(rs,"type_name"))[0];

            // 再删除
            // 总表
            p = connection.prepareStatement(sql2);
            p.setString(1,vehicleId);
            p.execute();

            String sql3 = "delete from "+Vehicle.getTypeName(type)+" where vehicle_id = ?";
            p = connection.prepareStatement(sql3);
            p.setString(1,vehicleId);
            return p.execute();
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection,p,rs);
        }
    }

    /**
     * 更新汽车信息
     * @param vehicleId 车牌号
     * @param brand 更新品牌
     * @param model 更新型号
     * @param perRent 日租金
     * @return 是否成功
     */
    public static boolean updateVehicle(String vehicleId, String type,String brand, String model, int perRent) {
        String sql1 = "update vehicle set perRent = ?,brand = ? where vehicleId = ?";
        String sql2 = "update "+Vehicle.getTypeName(type)+" set model = ? where vehicle_id = ?";

        Connection connection = DBUtils.getConnection();
        PreparedStatement p = null;

        try {
            p = connection.prepareStatement(sql1);
            p.setInt(1,perRent);
            p.setString(2,brand);
            p.setString(3,vehicleId);

            p.execute();

            p = connection.prepareStatement(sql2);
            p.setString(1,model);
            p.setString(2,vehicleId);

            p.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection,p,null);
        }
    }

    public static boolean addVehicle(String vehicleId, String type,String brand, String model, int perRent) {


        String sql1 = "select vehicleId from vehicle where vehicleId = ?";
        String sql2 = "insert into vehicle(brand, vehicleId, perRent, type) values (?,?,?,?)";
        String sql3 = "insert into "+Vehicle.getTypeName(type)+"(vehicle_id, model) values(?,?)";

        Connection connection = DBUtils.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;

        try {
            //先判断是否有这个车牌号
            p = connection.prepareStatement(sql1);
            p.setString(1,vehicleId);

            rs = p.executeQuery();
            // 如果存在车牌号,则不添加
            if(rs.next()) return false;

            p = connection.prepareStatement(sql2);
            p.setString(1,brand);
            p.setString(2,vehicleId);
            p.setInt(3,perRent);
            p.setInt(4,Vehicle.getTypeId(type));
            p.execute();

            p = connection.prepareStatement(sql3);
            p.setString(1,vehicleId);
            p.setString(2,model);
            p.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection,p,rs);
        }
    }
}