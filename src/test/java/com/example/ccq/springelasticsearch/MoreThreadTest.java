package com.example.ccq.springelasticsearch;

/**
 * 一个多线程死锁的例子
 */
public class MoreThreadTest {
    private static Object lock1=new Object();
    private static Object lock2=new Object();

    public static void main(String[] args) {
       new Thread(){
           @Override
           public void run(){
               synchronized (lock1){
                   System.out.println("thread1 get lock1");
                   try{
                       Thread.sleep(100);
                   }
                   catch (Exception e){
                        e.printStackTrace();
                   }
                   synchronized (lock2){
                       System.out.println("thread1 get lock2");
                   }
               }
               System.out.println("thread1 end");
           }

       }.start();


        new Thread(){
            @Override
            public void run(){
                synchronized (lock2){
                    System.out.println("thread2 get lock2");
                    try{
                        Thread.sleep(100);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    synchronized (lock1){
                        System.out.println("thread2 get lock1");
                    }
                }
                System.out.println("thread2 end");
            }

        }.start();
    }
}
