package com.example.ccq.springelasticsearch.dao;

import com.example.ccq.springelasticsearch.pojo.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author ZTY
 * @title: ItemRepository
 * @description:
 * @Description:定义ItemRepository 接口
        * @Param:
       * 	Item:为实体类
      * 	Long:为Item实体类中主键的数据类型

 * @date 2019/7/1214:27
 */
public interface ItemRepository extends ElasticsearchRepository<Item,Long> {

    //根据价格区间查询
    List<Item> findByPriceBetween(double price1,double price2);

}
