package com.cxh.thread;

import org.junit.Test;

public class DeadLockTest {
	
	@Test
	public void deadLockExample(){
		//开启两个线程，演示死锁的产生
		
	}
	
	public static void main(String[] args) {
		new Thread(new DeadLock(true)).start();
		new Thread(new DeadLock(false)).start();
	}
}
