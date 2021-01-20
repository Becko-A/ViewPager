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
        loginUser.setDept(user.getDept());
        loginUser.setPhone(user.getPhone());
        loginUser.setQQ(user.getQQ());
        loginUser.setEducation(user.getEducation());
        loginUser.setSchool(user.getSchool());
        loginUser.setStuID(user.getStuID());
        loginUser.setWechat(user.getWechat());
    }
    public void userLogout(){
        loginUser = new User();
    }
}

