package com.ysq.service;

import com.ysq.pojo.User;

public class LoginServe {
    public static User login(String userName,String password) {
        User user = checkUser(userName);
        // 检查密码是否一致
        if(user!=null) {
            // 一致检查密码是否正确
            if(checkLogin(user,password)) return user;
        }

        return null;
    }

    public static User checkUser(String userName) {
        // 找user_pojo
        return User.getUserWithUserName(userName);
    }

    public static boolean checkLogin(User user,String password) {
        return user.checkPassWord(password);
    }
}
