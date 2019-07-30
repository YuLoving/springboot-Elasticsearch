package com.example.ccq.springelasticsearch.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限
 */
@Data
public class Module implements Serializable {
    //权限id
    private Integer mid;
    //权限名称
    private String mname;

}
