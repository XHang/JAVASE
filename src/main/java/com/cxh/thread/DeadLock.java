package com.cxh.thread;

/**
 * 死锁演示程序
 * @author Administrator
 * 所谓死锁，其实就是嵌套同步
 * 死锁的产生需要两个线程，访问不同的代码但又有所关联
 * 这个关联就是同步。
 * 具体怎么产生死锁的呢。
 * 当在这个代码中，线程A和线程B同时分别拿到锁A和锁B的时候。
 * 再执行下去，就死锁了。
 * 因为线程A想继续下去就需要拿到锁B，但是锁B被线程B拿了。（拿来，不给，╭(╯^╰)╮）
 * 同理线程B也是这样子的
 */
public class DeadLock  implements Runnable{

	//这个flag是用来标识不同的线程的，然后根据这个标识，不同的线程走不同的代码
	private boolean flag;
	public DeadLock(boolean flag){
		this.flag = flag;
	}
	@Override
	public void run() {
		//线程A的执行线路
		if(this.flag){
			System.out.println("线程A已经进入执行线路");
			synchronized (Lock.lockA) {
				//在此处，线程A休眠几秒,让线程B拿到锁B，造成死锁
				System.out.println("线程A成功拿到锁A了！");
				try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
				synchronized (Lock.lockB) {
					System.out.println("线程A成功拿到锁B了！");
				}
				System.out.println("线程A释放了锁B！");
			}
			System.out.println("线程A释放了锁A！");
		//线程B的执行线路
		}else{
			System.out.println("线程B已经进入执行线路");
			synchronized(Lock.lockB){
					System.out.println("线程B成功拿到锁B了！");
				synchronized(Lock.lockA){
					System.out.println("线程B成功拿到锁A了！");
				}
				System.out.println("线程B释放了锁A！");
			}
			System.out.println("线程B释放了锁B！");
		}
	}
}
