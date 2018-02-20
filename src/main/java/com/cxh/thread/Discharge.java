package com.cxh.thread;

import com.cxh.vo.Box;

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
			synchronized (box) {
				if(box.isEmpty()){
					System.out.println("箱子已经空了，那我先睡一觉，有货再叫我哦");
					try {box.wait();} catch (InterruptedException e) {System.out.println(e.getMessage());}
				}
				System.out.println("开始卸货");
				System.out.println("从容器中取得货物"+box.resourcesName+",其数量是"+box.resourcesNum);
				box.resourcesName=null;
				box.resourcesNum=null;
				System.out.println("卸货完毕");
				box.notify();
				System.out.println("喂，哥们，醒醒，该你装货了");
			}
		}
	}
}
