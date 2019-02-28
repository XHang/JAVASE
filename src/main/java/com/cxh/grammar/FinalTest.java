package com.cxh.grammar;

/**
 * 演示一下final的妙用
 */
public class FinalTest {

    public static void main(String[] args)  {
        String a = "hello2";
        final String b = "hello";
        String d = "hello";
        String c = b + 2;
        String e = d + 2;
        System.out.println((a == c));  //true
        System.out.println((a == e));  //fasle
        String g = "hello"+2;
        //为什么,因为b变量是预编译常良,在编译时期,就可以确定b的值,所以编译后,以上的代码实际上是变成这样
//        String a = "hello2";
//        String d = "hello";
//        String c = "hello"+2=====>hello2
//        String e = d + 2;
//        System.out.println((a == c));  //true
//        System.out.println((a == e));  //fasle

    }
}
