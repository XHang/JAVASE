package com.cxh.thread;

import com.cxh.vo.Resources;

/**
 * 线程间通信的输入线程
 * @author Administrator
 *
 */
public class OuterThread  implements Runnable{

	private Resources resources= null;
	
	public OuterThread (Resources resources){
		this.resources = resources;
	}
	
	@Override
	public void run() {
		while(true){
			if(resources.isEmpty(resources)){
				System.err.println("货物是空的，卸货，不可能");
			}
			System.out.println("开始卸货");
			System.out.println("从容器中取得货物"+resources.resourcesName+",其数量是"+resources.resourcesNum);
			resources.resourcesName=null;
			resources.resourcesNum=0;
			System.out.println("卸货完毕");
		}
	}

}
