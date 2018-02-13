package com.cxh.thread;
/**
 * java 多线程的实现方式之一：继承 Thread
 * @author Mr-hang
 */
public class ThreadExtendsImpl  extends Thread{

	public ThreadExtendsImpl(String threadName){
		super(threadName);
	}
	@Override
	public void run() {
		//该线程执行两百次循环，每一次循环都打印线程名
		for(int i=0;i<200;i++){
			System.out.println(ThreadExtendsImpl.currentThread().getName()+".....run");
			super.run();
		}
	}

}
