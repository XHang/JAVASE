package com.cxh.generics;

/**
 * 泛型类上面可以定义一个类型
 * 类里面就可以使用这个类型的对象了
 * @author Mr-hang
 * @param <E>
 */
public class GenericsClass<E>	{
	private E e;
	public void SetObject(E e){
		this.e=e;
	}
	public E getObject(){
		return e;
	}
}