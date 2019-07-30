package com.example.ccq.springelasticsearch.service;

import com.example.ccq.springelasticsearch.pojo.User;
import com.example.ccq.springelasticsearch.pojo.role;

public interface Userservice {

    //通过名字来查询用户、角色、权限
    User findByUserName(String username);

}
