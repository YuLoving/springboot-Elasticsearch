package com.example.ccq.springelasticsearch.utils.HTTP;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * HttpComponents 包括 HttpCore包和HttpClient包
 * HttpClient：Http的执行http请求
 * DefaultHttpClient：httpClient默认实现
 * HttpGet、HttpPost：Get、Post方法执行类
 * HttpResponse：执行返回的Response，含http的header和执行结果实体Entity
 * HttpEntity：Http返回结果实体，不含Header内容
 * HttpParam：连接参数，配合连接池使用
 * PoolingClientConnectionManager：连接池
 */
public class BasicGetAndPostTest {
    public static void main(String[] args) throws IOException {
      /**
      //基础Get方法
        String url="http://localhost:8186/get";
            //默认的client类
        HttpClient client  = new DefaultHttpClient();
            //设置为get连接的方式
        HttpGet httpGet = new HttpGet(url);
            //得到返回的结果
        HttpResponse response = client.execute(httpGet);
        // 得到返回的client里面的实体对象信息.
        HttpEntity entity = response.getEntity();
        if (entity!=null){
            System.out.println(entity.getContentEncoding());
            System.out.println(entity.getContentType());
            //得到返回的主体内容
            InputStream stream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            System.out.println(reader.readLine());
        }
        //关闭连接
        client.getConnectionManager().shutdown();
        */

        //基础的post方法

        String url ="http://localhost:8186/post";
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        //添加参数
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("p","1"));
        nameValuePairs.add(new BasicNameValuePair("t","2"));
        nameValuePairs.add(new BasicNameValuePair("e","3"));

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
        httpPost.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity en = response.getEntity();
        System.out.println("post请求的返回结果："+en.getContent());
        //关闭连接
        httpClient.getConnectionManager().shutdown();


    }




}
