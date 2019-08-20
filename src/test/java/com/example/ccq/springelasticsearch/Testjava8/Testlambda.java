package com.example.ccq.springelasticsearch.Testjava8;

import java.util.*;

/**
 *
 *
 * java8中一个非常重要的特性就是lambda表达式，我们可以把它看成是一种闭包，它允许把函数当做参数来使用，
 * 是面向函数式编程的思想，一定程度上可以使代码看起来更加简洁。例如以前我们使用匿名内部类来实现代码：
 * Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）
 * lambda表达式语法
 *  （左边）输入参数->（右边）lambda主体
 *     (paramters) -> expression；
 *
 * 1）输入参数：
 * 　　可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
 * 　　可选的参数圆括号：
 * 　　　　a、一个参数无需定义圆括号，但多个参数需要定义圆括号。
 * 　　　　b、如果申明了参数类型，则一定需要圆括号。
 * （2）lambda主体：
 * 　　可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
 * 　　可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。


 *     (paramters) -> {statements;}
 *     展开如：
 *     (Type1 param1, Type2 param2, Type3 param3, ...) -> {
 *         statement1;
 *         statement2;
 *         statement3;
 *         ...
 *         return statementX;
 *     }
 *
 *     lambda表达式特征
 *  可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
 *  可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
 *  可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
 *  可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。
 */
public class Testlambda {

    public static void main(String[] args) {
   /*    // 1）没有参数的表达式：
        ()-> System.out.println("没有参数的Lambda表达式");
        //（2）只有一个参数的表达式：
        (X)-> System.out.println("只有一个参数的Lambda表达式");

       //说明：一个参数的可以不用使用()，如果参数声明了参数类型则必须要加()；Lambda主体是语句块的话需要使用{}。

        //3）有两个或者多个参数的表达式：
        (x,y) -> System.out.println("these are several parameters Lambda expression");*/

        /*List<String> list = Arrays.asList("我的", "asda", "dasda", "wode", "尴尬");
        list.forEach((n)-> System.out.println(n));*/

        //1.数组的遍历
        Integer [] items={1,2,3};
        System.err.println("Lambda 表达式遍历 Array 数组");
        Arrays.asList(items).forEach(item-> System.out.println(item));

        //2.list的遍历
        System.err.println("Lambda 表达式遍历list集合");
        List<Object> list = new ArrayList<>();
        list.add("基久1");
        list.add("基久2");
        list.add("基久3");
        list.stream().forEach(a-> System.out.println(a));

        //3.map的遍历
        System.err.println("Lambda 表达式遍历map集合");
        Map<String, Object> map = new HashMap<>();
        map.put("p1","假的");
        map.put("p2",2);
        map.put("p3","dsdsds");
        map.forEach((k,v)-> {System.out.println("键："+k+",值："+v);});
    }


}
