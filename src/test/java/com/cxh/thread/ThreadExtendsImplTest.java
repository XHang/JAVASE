package com.cxh.thread;

import org.junit.Test;

/**
 * 多线程测试类
 * @author Mr-hang
 */
public class ThreadExtendsImplTest {
	
	@Test
	public void runTest(){
		ThreadExtendsImpl thread1 = new ThreadExtendsImpl("thread1");
		ThreadExtendsImpl thread2 = new ThreadExtendsImpl("thread2");
		//开启两个线程，随便执行
		//值得注意的是，线程对象start开启
		thread1.start();
		thread2.start();
	}
}
