package com.cxh.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
	
	private HelloWorld helloworld;
	
	
	/**
	 * 代理策略类传入被代理的实现类对象，以便当代理方法执行后，能执行之后的非代理方法
	 * @param helloworld
	 */
	public MyInvocationHandler(HelloWorld helloworld) {
		super();
		this.helloworld = helloworld;
	}



	/**
	 * 拦截被代理对象的所有方法
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before say hello world");
		//注意：下一行的意思是调用方法，参数proxy是哪个对象的方法，如果你指定的参数是一个proxy对象的话.
		//恩，它会再次执行proxy对象的方法，也就是再次执行MyInvocationHandler类的invoke方法，于是就死循环了
		//所以实际上，要将参数proxy的对象换成非代理对象，执行非代理对象里面的方法
		//不过，我倒想到了。。这样不就可以不用建实现类直接用接口生成代理对象吗？
		//厉害了
		return method.invoke(helloworld, args);
	}

}
