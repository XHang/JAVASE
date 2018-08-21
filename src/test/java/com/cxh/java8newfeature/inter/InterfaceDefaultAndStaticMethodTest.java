package com.cxh.java8newfeature.inter;

import org.junit.Test;

import com.cxh.java8newfeature.impl.InterfaceDefaultAndStaticMethodImpl;

public class InterfaceDefaultAndStaticMethodTest {
	@Test
	public void testDoSth(){
		//不用接触到实现类，全都是接口化编程
		InterfaceDefaultAndStaticMethod obj = InterfaceDefaultAndStaticMethod.getInstance();
		
	}
}
