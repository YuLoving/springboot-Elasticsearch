package com.example.ccq.springelasticsearch.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限
 */
@Data
public class permission implements Serializable {
    private Integer id;
    //权限id
    private String permissionId;
    //权限名称
    private  String name;
    //权限描述
    private String description;
    //权限访问路径
    private String url;
    //权限标识
    private  String perms;
    //父级权限id
    private Integer parentId;
    //类型   0：目录   1：菜单   2：按钮
    private int type;
    //排序
    private  int order_num;
    //图标
    private  String icon;
    //状态：1有效；2删除
    private int status;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;


}
