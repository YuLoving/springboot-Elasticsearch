package com.example.ccq.springelasticsearch.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class User implements Serializable {
    //用户ID
    private Integer uid;

    //用户名
    private String username;
    //密码
    private  String password;

    /**
     * 用户和角色是多对多
     */
    private Set<role> roles=new HashSet<>();





}
