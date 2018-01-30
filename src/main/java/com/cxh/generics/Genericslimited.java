package com.cxh.generics;
/*
 * 泛型限定，写出操作集合比较方法的泛型限定，包括上限和下限
 * 思路：上限是父类指定，所有父类的子类都可以接受
 * 所以比较方法传入的 参数是父类类型，并传入子类对象，看能不能比较
 * 若能，则理解正确
 * 同理，写出下限
 * 弊端是只能使用父类的方法
 * 我只打印Person和Persom的子类
 */
public class Genericslimited {

}
