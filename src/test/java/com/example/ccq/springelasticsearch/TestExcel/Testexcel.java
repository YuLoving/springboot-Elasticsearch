package com.example.ccq.springelasticsearch.TestExcel;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.example.ccq.springelasticsearch.pojo.ExcelTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Testexcel {

    public static void main(String[] args) throws Exception {
        FileOutputStream outputStream = new FileOutputStream("D:\\测试.xlsx");
        try {

            ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);
            //写第一个sheet, sheet1  数据全是List<String> 无模型映射关系
            Sheet sheet = new Sheet(1,0, ExcelTest.class);

            List<ExcelTest> list = new ArrayList<>();
            ExcelTest test = new ExcelTest();
            test.setName("小白");
            test.setSax("男");
            test.setAddress("北京");
            test.setEmail("1314520@163.com");
            test.setAge("22");
            test.setHeigh("180");
            test.setLast("终极辅助");

            ExcelTest test1 = new ExcelTest();
            test1.setName("linda");
            test1.setSax("女");
            test1.setAddress("美国");
            test1.setEmail("1314520@163.com");
            test1.setAge("24");
            test1.setHeigh("165");
            test1.setLast("美少女战士");

            list.add(test);
            list.add(test1);
            writer.write(list,sheet);
            writer.finish();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
