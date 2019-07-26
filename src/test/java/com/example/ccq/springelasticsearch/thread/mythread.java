package com.example.ccq.springelasticsearch.thread;

/**
 * 通过继承 thread实现多线程
 */
public class mythread  extends Thread{
    private  int count =10;
    public synchronized  void run(){
        if(count>0){
            count--;
        }
    }



}
