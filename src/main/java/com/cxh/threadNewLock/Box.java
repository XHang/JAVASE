package com.cxh.threadNewLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 等待唤醒机制
 * 该类是生产者/消费者线程使用的共享资源
 * 并且维护了
 * 1.多线程运行的两个代码(装箱、卸箱)
 * 2.线程的两个状态Condition对象，方便在其他线程唤醒休眠线程
 * 3. 锁对象，使之所有被锁定的代码块同时只能有一个线程在执行
 * 
 * 
 */
public class Box {

	/**
	 * 资源名
	 */
	private String resourcesName;
	
	/**
	 * 资源数
	 */
	private Integer resourcesNum;
	
	private Lock lock = new ReentrantLock();
	
	private Condition putCondition = lock.newCondition();
	
	private Condition takeOutCondition = lock.newCondition();
	
	public boolean isEmpty(){
		return (this.resourcesName==null || this.resourcesNum==null);
	}
	
	/**
	 * 生产者放置物品，首先加锁，其次判断箱子是否非空，非空则睡眠
	 * 睡眠结束或者箱子为空则放置东西进箱子
	 */
	public void put(){
		try{
			lock.lock();
			if(!this.isEmpty()){
				System.out.println("箱子里面还有东西，我先睡下觉，没货了再叫我");
				try {putCondition.await();} catch (InterruptedException e) {e.printStackTrace();}
			}
			//加锁，取到该锁后，消费者那边被锁住的同步代码将无法再访问
			System.out.println("正在装货");
			this.resourcesName="充气娃娃泰国版";
			this.resourcesNum=233;
			System.out.println("装货完毕");
		}finally{
			//发送信号给消费者唤醒该线程
			takeOutCondition.signal();
			System.out.println("哥们，醒醒，可以卸货了");
			//解锁，消费者可以取到锁
			lock.unlock();
		}
	}
	
	public void taskOut(){
		try{
			lock.lock();
			if(isEmpty()){
				System.out.println("阿勒，已经没货了，那我先睡会，有货再叫我");
				try {takeOutCondition.await();} catch (InterruptedException e) {e.printStackTrace();}
			}
			//加锁，取到该锁后，生产者那边被锁住的同步代码将无法再访问
			System.out.println("从箱子取出物品"+this.resourcesName+"共"+this.resourcesNum+"个");
			this.resourcesName=null;
			this.resourcesNum=null;
		}finally{
			//发送信号让生产者醒过来,这个唤醒必须在同步代码块里面执行，否则会抛 java.lang.IllegalMonitorStateException
			putCondition.signal();
			//解锁，生产者可以取到锁
			lock.unlock();
			
		}
	}
	
}
