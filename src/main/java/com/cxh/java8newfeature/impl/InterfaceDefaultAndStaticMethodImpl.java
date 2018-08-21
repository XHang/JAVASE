package com.cxh.java8newfeature.impl;

import com.cxh.java8newfeature.inter.InterfaceDefaultAndStaticMethod;

public class InterfaceDefaultAndStaticMethodImpl  implements InterfaceDefaultAndStaticMethod{

	@Override
	public void doSth() {
		System.out.println("InterfaceDefaultAndStaticMethod的默认实现类");
	}

}
