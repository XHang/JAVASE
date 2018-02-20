package com.cxh.vo;

/**
 * 等待唤醒机制
 * 两个线程使用的共享资源

 */
public class Box {

	/**
	 * 资源名
	 */
	public String resourcesName;
	
	/**
	 * 资源数
	 */
	public Integer resourcesNum;
	
	public boolean isEmpty(){
		return (this.resourcesName==null || this.resourcesNum==null);
	}
}
