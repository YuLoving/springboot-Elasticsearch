package com.example.ccq.springelasticsearch.config.Excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.ccq.springelasticsearch.dao.EasyExcelmapper;
import com.example.ccq.springelasticsearch.pojo.Excelpojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/** 解析监听器，
 * 每解析一行会回调invoke()方法。
 * 整个excel解析结束会执行doAfterAllAnalysed()方法
 * 下面只是我写的一个样例而已，可以根据自己的逻辑修改该类。
 */
@Component
public class ExcelListener  extends AnalysisEventListener {

    @Autowired
    private EasyExcelmapper easyExcelmapper;


    //自定义用于暂时存储data。
    //可以通过实例获取该值
    private List<Object> datas=new ArrayList<>();

    public List<Object> getDatas() {
        return datas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }

    @Override
    public void invoke(Object o, AnalysisContext context) {
        System.out.println("当前行："+context.getCurrentRowNum());
        System.out.println(o);
        //数据存储到list，供批量处理，或后续自己业务逻辑处理。
        datas.add(o);
        //根据自己业务做处理
       doSomething(o);
    }

    private void doSomething(Object object) {
        Excelpojo excel = (Excelpojo) object;
        //添加到数据库
        easyExcelmapper.add(excel);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // datas.clear();//解析结束销毁不用的资源
    }


}
