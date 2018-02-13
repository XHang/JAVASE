package com.cxh.thread;

/**
 * 
 * 模拟售票程序（抽象售票站）
 * @author Mr-hang
 */
public class TicketConSumptioner implements Runnable{
	//初始化票数100张
	private int ticket = 100;
	
	Object  object = new Object();
	@Override
	public void run() {
		while(true){
			synchronized (object) {
				if(this.ticket>0){
					//让票价
					try{Thread.sleep(10);}catch(Exception e){}
				}
			}
		}
	}

}
