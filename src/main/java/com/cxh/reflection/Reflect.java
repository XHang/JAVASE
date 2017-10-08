package com.cxh.reflection;
import java.lang.reflect.Constructor;


public class Reflect {

	/**
	 * 运行函数，从注释里选择你要测试的部分
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws  Exception{
		
		//Constructor con=clazz.getConstructor(int.class,String.class);
		 getConstructor_2();

	}
	
	/**
	 * 演示无参数的构造函数的获取方式
	 * @throws Exception
	 */
	public static void getConstructor_1() throws Exception	
	{
		String className="Demo";
		Class<?> clazz=Class.forName(className);
		Object object=clazz.newInstance();
		System.out.println(object);
	}
	
	/**
	 * 演示带参数的构造方法
	 * @throws Exception
	 */
	public static void getConstructor_2() throws Exception	
	{
		String className="Demo";
		Class<?> clazz=Class.forName(className);
		Constructor<?> con=clazz.getConstructor(int.class,String.class);
		Object object=con.newInstance(4,"zhansan");
		System.out.println(object);
	}

}
