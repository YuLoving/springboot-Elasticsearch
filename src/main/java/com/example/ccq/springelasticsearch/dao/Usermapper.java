package com.example.ccq.springelasticsearch.dao;

import com.example.ccq.springelasticsearch.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Usermapper {

    //通过名字来查询用户
    User findbyusername(String name);

    //通过ID查用户信息、角色、权限
    User findbyId(String userid);
}
