package com.example.ccq.springelasticsearch.Testjava8;

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

    }
}
