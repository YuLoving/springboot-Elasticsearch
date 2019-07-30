package com.example.ccq.springelasticsearch.controller;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/index")
    public String toindex(){
        return "index";
    }


    @GetMapping("/login")
    public String tologin(){
        return "login";
    }
    /**
     * 测试登录
     */
    @PostMapping("/tologin")
    @ResponseBody
    public Object login(String name, String pwd){
        Map<String, Object> map = new HashMap<>();
        System.out.println("name:"+name+",pwd:"+pwd);
        if(name.equals("aaa") && pwd.equals("123")){
            //登录失败
            map.put("code",200);
            map.put("msg","/test/index");
        }else{
            //登录成功
            map.put("code",500);
            map.put("msg","/test/login");
        }
        return map;

    }



    @GetMapping("/123")
    @ResponseBody
    public String get123(String aa) throws UnsupportedEncodingException {
        String s = URLDecoder.decode(aa, "utf-8");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return s;
    }

}
