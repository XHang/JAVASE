package com.cxh.proxy;

/**
 * 需要代理的类
 * @author Mr-hang
 *
 */
public class HelloWorldImpl  implements HelloWorld{

	@Override
	public void speakHelloworld() {
		System.out.println("Hello world");
	}

}
