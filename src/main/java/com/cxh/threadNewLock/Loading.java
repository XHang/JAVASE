package com.cxh.threadNewLock;


/**
 * 多线程间通信程序的一个输入（生产者，装货）线程
 * 完成的任务是：如果货物不为空，则进入睡眠状态，直到消费者叫醒他
 * 然后生产货物。最后再唤醒消费者
 * @author Administrator
 *
 */
public class Loading  implements Runnable{

	
	private Box box;
	
	public Loading(Box box){
		this.box = box;
	}
	@Override
	public void run() {
		while(true){
			box.put();
		}
	}
}
