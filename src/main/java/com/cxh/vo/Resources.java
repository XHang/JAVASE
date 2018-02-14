package com.cxh.vo;

/**
 * 等待唤醒机制
 * 两个线程使用的共享资源
 * 这个资源相当于一辆车，里面的属性才是真正的资源
 */
public class Resources {

	/**
	 * 资源名
	 */
	public String resourcesName;
	
	/**
	 * 资源数
	 */
	public int resourcesNum;
	
	/**
	 * 标识这个资源是否正忙
	 */
	public boolean busy;
	
	public boolean isEmpty(Resources resources){
		return (resources.resourcesName==null || resources.resourcesNum==0);
	}
}
