package com.cxh.reflection;
/*
 * *
 * 该类的作用是作为被反射的java类。
 * 提供一个空参数的构造函数和非空参数的构造函数
 */
public class Demo {
	int age;
	String name;
	Demo(){}
	 Demo(int age,String name)
	{
		
	}
	void run()
	{
		System.out.println("方法run正在运行中");
	}
}
