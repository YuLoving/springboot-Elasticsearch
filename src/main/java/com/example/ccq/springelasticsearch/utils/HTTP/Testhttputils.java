package com.example.ccq.springelasticsearch.utils.HTTP;

import java.util.HashMap;
import java.util.Map;

public class Testhttputils {
    public static void main(String[] args) throws Exception {
        //get请求
       /* String url="http://localhost:8186/get";
        Map<String, String> map = new HashMap<>();
        map.put("a","1");
        map.put("b","2");


        String s = HttpClientUtil.doGet(url,map);
        System.out.println(s);*/


       /* String url="http://localhost:8186/postjson";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("唉唉","1");
        jsonObject.put("aa","2");
        System.err.println(jsonObject.toString());
        String s = HttpClientUtil.doPostJson(url,jsonObject.toString());
        System.out.println(s);*/


        String url="http://localhost:8186/postaa";
        Map<String, String> map = new HashMap<>();
        map.put("a","1");
        map.put("b","2");
        System.err.println(map.toString());

        String s = HttpClientUtil.doPost(url,map);
        System.out.println(s);
    }
}
