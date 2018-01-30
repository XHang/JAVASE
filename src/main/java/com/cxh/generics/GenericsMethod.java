package com.cxh.generics;

/**
 * 泛型方法演示
 * @author Mr-hang
 *
 */
public class GenericsMethod {
	/**
	 * 参数是什么类型，返回值就是什么类型
	 * @param t
	 * @return
	 */
	public <T> T get(T t){
		return t;
	}
	//静态方法的泛型
	public static <U> void println(U u){
		System.out.println(u);
	}
}
