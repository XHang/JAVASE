package com.cxh.thread;
/**
 * 程序功能，线程开始运行后进入等待阶段，等到该线程被其他线程中断。
 * 则设置退出标示为真，下次运行将自动退出
 * @author Mr-hang
 *
 */
public class StopThread  implements Runnable{
	//线程循环/结束标志位
	private boolean flag=true;		
	//线程循环开始
	//介绍下我们线程锁的新成员，方法锁。不用搞个对象充当锁的对象，毕竟，程序员是不会有对象的，不是吗
	@Override
	public synchronized void  run(){
		while(flag)	{
			try{	
				System.out.println(Thread.currentThread().getName()+".....哈哈，我还活着");
				//模拟特殊情况，令线程冻结
				System.out.println(Thread.currentThread().getName()+".....不过我好困，先碎一觉再说");
				this.wait();						
			}
			catch(InterruptedException e){
				System.out.println(Thread.currentThread().getName()+"Exception");		
				//置标志位为假,以便下面的循环中能终止线程
				flag=false;																
			}
		}
		System.out.println(Thread.currentThread().getName()+".....额，我觉得。。我觉得。。我还可以抢救下");
		
	}
}
