package com.cxh.generics;
//泛型接口
interface Inter <T>	{
	public void show(T t);
}
/*
 * 实现方法1：实现接口并指定引用数据类型字符串
class InterImpl implements Inter<String>{
	public void show(String t){
		System.out.println(t);
	}
}
*/
//实现方法2，我子类也TM不知道自己要操作什么引用数据类型
class InterImpl <T> implements Inter<T>{
	public void show(T t){
		System.out.println(t);
	}
}
