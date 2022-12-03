package com.ysq.dao;

import com.ysq.pojo.User;
import com.ysq.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    /**
     * 通过用户名查询用户及信息
     * @param userName 提供用户名
     * @return 对应用户
     */
    public static User queryUserWithUserName(String userName) {
        Connection connection = DBUtils.getConnection();
        PreparedStatement p = null;
        ResultSet rs = null;

        try {
            p = connection.
                    prepareStatement("select user_name, password,permission from user where user_name = ?");
            p.setString(1,userName);
            rs =  p.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(rs==null) return null;
        String password = null;
        String permission = null;
        try {
            if (rs.next()) {
                userName = rs.getString(1);
                password = rs.getString(2);
                permission = rs.getString(3);
            }
            return new User(userName,password,permission);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection,p,rs);
        }
    }
}
