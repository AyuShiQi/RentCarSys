package com.ysq.pojo;

import com.ysq.dao.UserDAO;

public class User {
    private String userName;
    private String password;
    private String permission;

    public User(String userName, String password, String permission) {
        this.userName = userName;
        this.password = password;
        this.permission = permission;
    }

    public static User getUserWithUserName(String userName) {
        return UserDAO.queryUserWithUserName(userName);
    }

    public String getUserName() {
        return userName;
    }

    public String getPermission() {
        return permission;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        // 连接数据库DAO
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String parseToJSON() {
        return "{" +
            "\"userName\": \"" + userName + "\"," +
            "\"permission\": \"" + permission +
        "\"}";
    }

    public boolean checkPassWord(String password) {
        return this.password.equals(password);
    }
}
