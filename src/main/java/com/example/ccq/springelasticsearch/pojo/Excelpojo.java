package com.example.ccq.springelasticsearch.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

@Data
public class Excelpojo  extends BaseRowModel {

    @ExcelProperty(index = 0 , value = "工号")
    private String staff_code;
    @ExcelProperty(index = 1 , value = "姓名")
    private String name;
    @ExcelProperty(index = 2 , value = "性别")
    private String sex;

    @ExcelProperty(index = 3,value = "出生年月",format ="yyyy/MM/dd")
    private String birth;

    @ExcelProperty(index = 4 , value = "联系电话")
    private String tel;



}
