package com.cxh.thread;

import com.cxh.vo.Box;

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
			synchronized(box){
					//如果箱子里面有货的话，进入等待状态
					if(!box.isEmpty()){
						System.out.println("箱子还有东西，那我先睡一觉，要装货时再叫我");
						try {box.wait();} catch (InterruptedException e) {System.err.println(e.getMessage());}
					}
					System.out.println("装货开始");
					this.box.resourcesName="苹果";
					this.box.resourcesNum=12;
					System.out.println("装货结束");
					box.notify();
					System.out.println("喂，哥们，醒醒，可以取货了");
			}
		}
	}
}
