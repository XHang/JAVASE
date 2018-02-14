package com.cxh.thread;

import com.cxh.vo.Resources;

/**
 * 多线程间通信程序的一个输入线程
 * @author Administrator
 *
 */
public class InputThread  implements Runnable{

	
	private Resources resources;
	
	public InputThread(Resources resources){
		this.resources = resources;
	}
	@Override
	public void run() {
		while(true){
			if(!resources.isEmpty(resources)){
				System.err.println("货物还没空！！");
			}
			System.out.println("装货开始");
			this.resources.resourcesName="苹果";
			this.resources.resourcesNum=12;
			System.out.println("装货结束");
		}
	}
	
}
