package com.example.ccq.springelasticsearch.thread;

import com.example.ccq.springelasticsearch.utils.CommonThreadPoolUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Test01 {
    public static void main(String[] args) {
        List<String> list = CommonThreadPoolUtils.execTaskes(Arrays.asList(
                () -> get(),
                () -> timeout()
        ));
       list.forEach((s)->{
           System.out.println("list遍历："+s);
       });


    }

    public static String get(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = format.format(new Date());
        System.out.println("开始异步第一个任务"+s);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String  a= format.format(new Date());
        System.out.println("异步第一个任务结束"+a);
        return "第一个异步结果";
    }
    private static int time=1500;
    // 超时1.5s
    public static String timeout(){
        long start=System.currentTimeMillis();
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end=System.currentTimeMillis()-start;
        System.out.println("挤时间："+end);
        if(end>=1500){
            return "1";
        }else{
            return "2";
        }

    }

}
