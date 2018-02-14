package com.cxh.thread;

import org.junit.Test;

import com.cxh.vo.Resources;

/**
 * 演示多线程间通信的安全问题--解决方案-等待唤醒机制
 * @author Administrator
 * 这个程序模拟的是一个生产者，消费者的双线程例子
 * 生产者负责生产资料，消费者负责消费资料，他们
 *
 */
public class WaitWakeThread {
	
	public static void main(String[] args) {
				//首先创建资源对象，确保资源对象唯一性，
				Resources resources=new Resources();		
				//传入资源对象，确保两个线程访问的是同一个资源
				InputThread inputThread=new InputThread(resources);			
				//传入资源对象，确保两个线程访问的是同一个资源
				OuterThread outThread=new OuterThread (resources);			
				new Thread(inputThread).start();			//开启输入线程
				new Thread(outThread).start();			//开启输出线程
	}
}
