package com.cxh.java8newfeature.inter;

import com.cxh.java8newfeature.impl.InterfaceDefaultAndStaticMethodImpl;

/**
 * 类功能：java8新特性：接口的默认方法与静态方法
 * @author Administrator
 *
 */
public interface InterfaceDefaultAndStaticMethod {
	 public static InterfaceDefaultAndStaticMethod getInstance(){
		 return new InterfaceDefaultAndStaticMethodImpl();
	 }
	 void doSth();
	 
	 default void newMethod(){
		 System.out.println("新加的默认方法");
		 
	 }
}
