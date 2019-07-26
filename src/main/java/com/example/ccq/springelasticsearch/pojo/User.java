package com.example.ccq.springelasticsearch.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class User implements Serializable {
    private Integer id;
    //用户ID
    private String userId;
    //用户名
    private String username;
    //密码
    private  String password;
    //加密盐值
    private  String salt;
    //邮箱
    private String email;
    //联系方式
    private  String phone;
    //性别：1男2女
    private  int sex;
    //年龄
    private int age;
    //用户状态：1有效; 2删除
    private String status;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createTime;

    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateTime;

    //最后登录时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date lastLoginTime;

    /**
     * 用户和角色是多对多
     */
    private Set<role> roles=new HashSet<>();





}
