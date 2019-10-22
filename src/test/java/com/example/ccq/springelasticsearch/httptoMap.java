package com.example.ccq.springelasticsearch;


import org.mortbay.util.MultiMap;
import org.mortbay.util.UrlEncoded;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author ZTY
 * @date 2019/10/11 17:44
 * 描述：
 */
public class httptoMap {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String url="http://localhost:8080/test?msg_signature=5c45ff5e21c57e6ad56bac8758b79b1d9ac89fd3&timestamp=1409659589&nonce=263014780tyTWESHep1vC5X9xho%2FqYX3Zpb4yKa9SKld1DsH3Iyt3tP3zNdtp%2B4RPcs8TgAE7OaBO%2BFZXvnaqQ%3D%3D";
        String newurl = URLDecoder.decode(url, "UTF-8");

        MultiMap multiMap = new MultiMap();


        UrlEncoded.decodeTo(newurl.substring(url.indexOf("?") + 1), multiMap,"UTF-8");
        System.out.println(multiMap.toString());
        System.out.println(multiMap.get("nonce"));


    }




}
