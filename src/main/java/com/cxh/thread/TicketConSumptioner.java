package com.cxh.thread;

/**
 * 
 * 模拟售票程序（抽象售票站）
 * 主要用于多线程下的线程并发安全性问题
 * 其使用案例在test程序包里面的ThreadExtendsImplTest类的main程序里面
 * @author Mr-hang
 */
public class TicketConSumptioner implements Runnable{
	//初始化票数100张
	private int ticket = 100;
	//new一个上帝对象来当锁
	Object  object = new Object();
	@Override
	public void run() {
		while(true){
			System.out.println("deal loop");
			//加了同步锁，确保同一时间内，锁里面的代码只能由一个线程执行
			synchronized (object) {
				System.out.println("into Synchronize");
				if(this.ticket>0){
					System.out.println("into Judge");
					//让售票站躺尸10ms
					try{Thread.sleep(10);}catch(Exception e){e.printStackTrace();}
					//发现没有，Thread.currentThread()通用性就在这里
					//顺带一提。--变量的含义是，是变量先减，然后再打印出来，这两个操作已经很接近原子性操作了。但是仍然还是属于两个操作
					//如果一旦在两个操作执行的中间插入一个线程进来，就会造成两个售票站卖同一张票的诡异现象。
					//当然了，这两个操作的时间太短，不太能模拟出来。但是在让票价卖出-1张还是可以的。
					//理由就是在if判断后的代码执行会稍微花一段时间，中间执行的过程要是半路杀出一个程咬金，那就好玩了
					System.out.println(Thread.currentThread().getName()+"售票站已卖出....."+ticket--);
				}else{
					break;
				}
			}
		}
		System.out.println("bey bey");
	}

}
