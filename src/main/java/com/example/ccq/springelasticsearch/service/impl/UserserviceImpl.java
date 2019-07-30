package com.example.ccq.springelasticsearch.service.impl;

import com.example.ccq.springelasticsearch.dao.Usermapper;
import com.example.ccq.springelasticsearch.pojo.User;
import com.example.ccq.springelasticsearch.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserserviceImpl implements Userservice {

    @Autowired
    private Usermapper usermapper;


    @Override
    public User findByUserName(String username) {
        return usermapper.findByUserName(username);
    }

}
