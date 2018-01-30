package com.cxh.proxy;

import java.lang.reflect.Proxy;

public class ProxyTest {
	public static void main(String[] args) {
		//生成代理类对象,第一个参数是classloader,第二个参数是代理类的接口，第三个参数是InvocationHandler，这个你懂吧。
		HelloWorld proxy = (HelloWorld) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), new Class[]{HelloWorld.class}, new MyInvocationHandler(new HelloWorldImpl()));
		//执行代理对象方法，该方法会被拦截的说
		proxy.speakHelloworld();
	}
}
