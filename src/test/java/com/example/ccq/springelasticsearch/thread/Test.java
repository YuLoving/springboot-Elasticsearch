package com.example.ccq.springelasticsearch.thread;

import com.example.ccq.springelasticsearch.utils.IdcardValidator;

public class Test {
    public static void main(String[] args) {
        String idcard = "320681199409051417";
        System.out.println(new IdcardValidator(idcard.toUpperCase()).validate());
    }
}
