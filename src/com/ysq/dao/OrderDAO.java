package com.ysq.dao;

import com.ysq.pojo.Order;
import com.ysq.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO {
    private OrderDAO() {}
    public static boolean setOrder(String userName,String vehicleId,double rents) {
        String sql1 = "insert into `order` (userName, vehicleId, rents, payed) values (?, ?, ?, default)";

        Connection connection = DBUtils.getConnection();
        PreparedStatement p = null;

        try {
            p = connection.prepareStatement(sql1);
            //先判断是否有这个车牌号
            p.setString(1,userName);
            p.setString(2,vehicleId);
            p.setDouble(3,rents);

            p.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection,p,null);
        }
    }

    public static String getOrder(String userName) {
        String sql1 = "select vehicleId,rents from `order` where userName=? and payed=false";

        Connection connection = DBUtils.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;

        try {
            p = connection.prepareStatement(sql1);
            p.setString(1,userName);

            rs = p.executeQuery();
            return Order.toUserJSONList(rs);
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection,p,rs);
        }
    }

    public static boolean updateOrder(String userName,String vehicleId,double rents) {
        String sql1 = "update `order` set payed=true where vehicleId= ? and userName = ? and rents = ?";

        Connection connection = DBUtils.getConnection();
        PreparedStatement p = null;

        try {
            p = connection.prepareStatement(sql1);

            p.setString(1,vehicleId);
            p.setString(2,userName);
            p.setDouble(3,rents);

            p.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection,p,null);
        }
    }


    public static String getAllCount() {
        String sql1 = "select count(*) as allCount from `order`";

        Connection connection = DBUtils.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;

        try {
            p = connection.prepareStatement(sql1);
            rs = p.executeQuery();
            if (rs.next()) {
                return rs.getString("allCount");
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection,p,rs);
        }
        return null;
    }

    private static String getPayCount(boolean what) {
        String sql1 = "select count(*) as rs from `order` where payed="+what;

        Connection connection = DBUtils.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;

        try {
            p = connection.prepareStatement(sql1);
            rs = p.executeQuery();
            if (rs.next()) {
                return rs.getString("rs");
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection,p,rs);
        }
        return null;
    }

    public static String getNoPayCount() {
        return getPayCount(false);
    }

    public static String getPayedCount() {
        return getPayCount(true);
    }

    public static String getAllRent() {
        String sql1 = "select sum(rents) as allRent from `order`";

        Connection connection = DBUtils.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;

        try {
            p = connection.prepareStatement(sql1);
            rs = p.executeQuery();
            if (rs.next()) {
                return rs.getString("allRent");
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection,p,rs);
        }
        return null;
    }
}
