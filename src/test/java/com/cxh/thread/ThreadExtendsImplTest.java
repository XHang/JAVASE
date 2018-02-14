package com.cxh.thread;

import org.junit.Test;

/**
 * 多线程测试类
 * @author Mr-hang
 */
public class ThreadExtendsImplTest {
	
	/**
	 * 测试线程的抢占性测试程序
	 */
	@Test
	public void runTest(){
		ThreadExtendsImpl thread1 = new ThreadExtendsImpl("thread1");
		ThreadExtendsImpl thread2 = new ThreadExtendsImpl("thread2");
		//开启两个线程，随便执行
		//值得注意的是，线程对象start开启后，不能再用这个线程对象重新开启线程了
		//否则会抛出非法状态异常
		thread1.start();
		thread2.start();
	}
	
	/**
	 * 售票程序
	 * 主要演示在票价总数一定的情况下，两个线程同时卖票。
	 * 怎么确保同一张票不会被卖两次。
	 * 也就是线程的并发安全性问题
	 * @param args
	 * 多线程的并发性问题还是在main函数里面测试会比较好
	 * junit测试框架测试的时候会造成线程突然间就挂的
	 */
	public static void main(String[] args) {
		/*TicketConSumptioner sellSite = new TicketConSumptioner();
		//建两个售票站，专门卖票
		Thread thread1 = new Thread(sellSite);
		Thread thread2 = new Thread(sellSite);
		//开始卖票
		thread1.start();
		thread2.start();*/
		new ThreadExtendsImplTest().stopThread();
	}
	
	/**
	 * 模拟下线程终止
	 * 现实含义：当线程由于某种特殊情况而冻结了，这个时候，只能强制打断。令其自生自灭
	 * 哈？你问我为什么不使用stop方法强制结束？因为人家过时了。
	 * 据说，这种处理方式是现在业界流行的方式。
	 * 让线程执行任务完自动die，很自然
	 */
	@Test
	public void stopThread(){
		StopThread runable = new StopThread();
		Thread thread = new Thread(runable);
		thread.start();
		int runCount =1;
		while(true){
			if(++runCount==1000){
				thread.interrupt();  //给线程1一砖头
				break;
			}
		}
	}
	
}
