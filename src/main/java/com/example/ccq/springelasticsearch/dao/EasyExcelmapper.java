package com.example.ccq.springelasticsearch.dao;

import com.example.ccq.springelasticsearch.pojo.Excelpojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EasyExcelmapper {

    /**
    批量插入
     */
    boolean moreadd( @Param("list") List<Excelpojo> list );

    /**
     * 单条添加
     * @param pojo
     * @return
     */
    boolean add(@Param("pojo") Excelpojo pojo);

}
