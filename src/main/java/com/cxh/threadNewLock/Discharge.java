package com.cxh.threadNewLock;


/**
 * 线程间通信的输出（消费者，卸货）线程
 * 完成的任务是：如果货物为空，则进入睡眠状态，直到生产者叫醒他
 * 然后消费货物。最后再唤醒生产者
 * @author Administrator
 *
 */
public class Discharge  implements Runnable{

	private Box box= null;
	
	public Discharge (Box box){
		this.box = box;
	}
	
	@Override
	public void run() {
		while(true){
			//消费者取出箱子的东西
			box.taskOut();
		}
	}
}
