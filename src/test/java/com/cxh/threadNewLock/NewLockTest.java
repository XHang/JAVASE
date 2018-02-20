package com.cxh.threadNewLock;

import org.junit.Test;

public class NewLockTest {
	
	/**
	 * 测试jdk1.5新加的加锁，解锁机制，以及睡眠，唤醒
	 * 以等待唤醒机制为例子
	 * 死了死了死了，完了完了完了完了，这个异常是什么鬼。
	 * 以后再解决吧
	 */
	@Test
	public void testNewLock(){
		Box box = new Box();
		Runnable putThread = new Loading(box);
		Runnable taskThread = new Discharge(box);
		new Thread(putThread).start();
		new Thread(taskThread).start();
	}
	public static void main(String[] args) {
		new NewLockTest().testNewLock();
	}
}
