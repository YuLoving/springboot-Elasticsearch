package com.example.ccq.springelasticsearch.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色
 */
@Data
public class role implements Serializable {
    private Integer id;
    //角色ID
    private String roleId;
    //角色名称
    private String name;
    //角色描述
    private  String description;
    //状态：1有效；2删除
    private int status;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createTime;
    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateTime;

    /**
     * 角色和权限也是多对多
     */
    private Set<permission> permissions=new HashSet<>();
}
