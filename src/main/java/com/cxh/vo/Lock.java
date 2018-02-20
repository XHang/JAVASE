package com.cxh.vo;

public class Lock {
	//定义两个锁，A和B
	//这两个锁属于多线程共享变量，可以用来演示死锁问题
	public static Object lockA = new Object();
	public static Object lockB = new Object();
}
