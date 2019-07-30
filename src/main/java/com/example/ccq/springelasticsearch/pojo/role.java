package com.example.ccq.springelasticsearch.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色
 */
@Data
public class role implements Serializable {
    private Integer rid;
    //角色ID
    private String rname;
    //角色名称
    /**
     * 角色和权限也是多对多
     */
    private Set<Module> modules =new HashSet<>();
}
