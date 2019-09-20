package com.example.ccq.springelasticsearch.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author ZTY
 * @date 2019/9/20 11:48
 * 描述：
 * java.security MessageDigest类
 * MessageDigest类
 * <p>
 * 介绍:信息摘要
 * java 1.6 api介绍:此类为应用程序提供信息摘要功能,如:MD5/SHA等.信息摘要是简单的单向哈希函数,它接收任意长度的数据,并返回固定长度的哈希值
 * <p>
 * 获得对象:
 * 一般通过getInstance("算法名称")方法获得
 * 支持的算法:
 * MD2
 * MD5
 * SHA-1
 * SHA-256
 * SHA-384
 * SHA-512
 * 常用方法;
 * 通过update()方法处理数据,任何时候都可以通过reset()方法重置摘要,一旦所有更新数据都更新完了,
 * 应该调用digest()方法完成哈希计算,对于定量的数据计算,digest()方法只能被调用一次,MessageDigest对象恢复到初始状态
 *
 *
 * clone();如果实现是可复制的,则返回一个副本
 * digest();通过执行诸如填充之类的操作完成哈希计算
 * digest(byte[] input)通过指定的数组完成最后更新操作,并计算哈希
 * digest(byte[] buf, int offset, int len)通过指定数组,并指定开始位置(偏移量),和数字长度,来进行最后更新,并计算哈希
 * getAlgorithm();返回指定的算法名称
 * getDigestLength()返回以字节为单位的摘要长度,如果实现不支持,则返回0
 * getInstance(String algorithm)返回指定算法的MessageDigest对象
 * getInstance(String algorithm, Provider provider)通过指定算法提供者和算法名称返回MessageDigest对象
 * getInstance(String algorithm, String provider)通过指定算法提供者和算法名称返回MessageDigest对象
 * getProvider();返回此信息摘要对象的提供者
 * isEquals();比较两个信息摘要对象的相等性
 * reset();重置摘要以供再次使用
 * toString();返回此信息摘要对象的字符串表示形式
 * update(byte input);通过指定的字节更新摘要
 * update(byte[] input)通过指定字节数组更新摘要
 * update(byte[] input, int offset, int len)通过指定字节数组从指定偏移量开始更新摘要
 * update(ByteBuffer input)通过指定ByteBuffer更新摘要
 */
public class Jiami {

    public static void main(String[] args) throws UnsupportedEncodingException {

        String a="bxLdikRXVbTPdHSM05e5u5sUoXNKd8-41ZO3MhKoyN5OfkWITDGgnr2fwJ0m9E8NYzWKVZvdVtaUgWvsdshFKA";
        String b="Wm3WZYTPz0wzccnW";
        String c="1414587457";
        String d="aa";

        String m="jsapi_ticket="+a+"&noncestr="+b+"&timestamp="+c+"&url="+d;
        System.out.println(m);
        String s = DigestUtils.sha1Hex(m);
        System.out.println(s);


    }

    /**
     * 这里是用别人封装好的方法来进行加密
     *
     * 需要导入两个架包：
     *
     * commons-codec-1.11.jar
     *
     * commons-lang3-3.7.jar
     *
     *
     * 例子： //需要加密的字符串
     *         String data = "跳梁小豆tlxd666";
     *         //md5加密
     *         String result = DigestUtils.md5Hex(data);
     *         System.out.println("md5加密后："+result);
     *
     *         //sha1加密
     *         result = DigestUtils.sha1Hex(data);
     *         System.out.println("sha1加密后："+result);
     */

        public   String StrToSha1(String str){
            return  DigestUtils.md5Hex(str);

        }
}
