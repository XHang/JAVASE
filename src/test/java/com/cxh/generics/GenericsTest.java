package com.cxh.generics;


public class GenericsTest {

	/**
	 * 泛型类使用示例
	 */
	public void GenericsClassTest(){
		GenericsClass<String> To=new GenericsClass<String>();
		To.SetObject(new String());
		String k=To.getObject();
	}
	
	public void GenericsInterfaceTest(){
		new InterImpl().show("O(∩_∩O)哈哈哈~"); //调用泛型接口的子类方法无需指定引用数据类型
		new InterImpl <Integer>().show(4);//调用方法2的接口子类需要指定数据类型。
	}
	
	public void GenericsMethodTest(){
		GenericsMethod.println("随便打印一个字符串");//直接用静态方法调用泛型。我并没有指定应该传入的是什么引用类型
		GenericsMethod d=new GenericsMethod();		//指定传入的参数是Person
		Integer o = d.get(new Integer(1));					
	}
}
