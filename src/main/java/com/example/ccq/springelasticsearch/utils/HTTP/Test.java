package com.example.ccq.springelasticsearch.utils.HTTP;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Request 的执行
 */
public class Test {
    public static void main(String[] args) throws IOException {
        /**
         * 1.将其想象成建一个浏览器的过程，
         * HttpClients我个人感觉可以类比Collection和Collections的关系，
         * 提供HTTPClient的工具
         */
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.可以想象为用什么方法去访问服务，就像表单提交时候选择Get还是Post
        HttpGet httpget = new HttpGet("http://www.baidu.com");
        //3.可以想象为点击鼠标的过程，或者是提交表单的过程。有返回值。。。。。
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpget);
            System.out.println("访问百度返回的结果："+response);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("访问报错："+e.getMessage());
        }finally {
            //关闭
            response.close();
        }
    }
}
