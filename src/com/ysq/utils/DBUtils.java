package com.ysq.utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBUtils {
    private static String url;
    private static String root;
    private static String password;

    static {
        //获取配置信息
        Properties config = new Properties();
        try {
            config.load(new FileReader("D:\\我的作业\\JAVA\\RentCar4.0\\RentCarSys\\src\\jdbc.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //连接信息
        String driver = (String)config.get("driver");
        url = (String)config.get("url");
        root = (String)config.get("root");
        password = (String)config.get("password");

        //连接ing
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,root,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void close(Connection connection, Statement st, ResultSet rs) {
        try {
            if(connection!=null){
                connection.close();
            }
            if(st!=null) {
                st.close();
            }
            if(rs!=null) {
                rs.close();
            }
        } catch (SQLException e) {
        }
    }
}
