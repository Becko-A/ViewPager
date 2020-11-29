package com.example.viewpager;

import android.app.Application;

public class MyApplication extends Application{
    public String appVersion = "v1.0";
    //当前登录用户
    private User loginUser = new User();
    public User getLoginUser(){
        return loginUser;
    }
    public void userLogin(User user){
        loginUser.setUserId(user.getUserId());
        loginUser.setToken(user.getToken());
        loginUser.setName(user.getName());
    }
    public void userLogout(){
        loginUser = new User();
    }
}
