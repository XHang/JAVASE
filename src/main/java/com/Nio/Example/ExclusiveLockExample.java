package com.Nio.Example;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 演示NIO中的排他锁
 * @author Administrator
 *   注：要获取一个排他锁，必须以写模式打开这个文件
 *   		  写模式的流：OutputStream    or    RandomAccessFile(可写模式)
 *   注：lock为堵塞方法，直到能获取到锁的时候才解除堵塞。
 *   用缓冲区的wrap方法后，把缓冲区的数据写进通道前，不需要调用flip方法
 *   如果在一个线程已经获取到排它锁时，第二个线程尝试写入数据会提示另一个线程已经锁定了文件的一部分，进程无法访问。
 *   如果该示例程序不去掉锁的机制后程序试验，则第二个线程可以重新写入。也就是说，获取该文件的通道后第二个程序也获取该文件的通道。
 *   并且两个程序都能写入数据。
 *   值得一提的是，不管写入线程有没有获得锁，只要它获取到通道后到通道释放前，windows的文件系统都不能写入数据。
 *   java倒能，腻害了我的咖啡
 *   
 */
public class ExclusiveLockExample{
	public static void main(String[] args) {
		System.out.println("主线程开启");
		//new Thread(new GetExclusiveLockThread("d://1.txt")).start();
		new Thread(new WriteThread("d://1.txt")).start();
	}
	
}
/**
 * 获得排他锁，睡眠1分钟，写数据，解锁
 * @author Administrator
 */
class GetExclusiveLockThread implements Runnable{
	String path;
	public GetExclusiveLockThread(String path){
		this.path=path;
	}
	public void run() {
		try {
			System.out.println("排它锁线程开启");
			//此通道开放写
			FileOutputStream out = new FileOutputStream(path);
			FileChannel  channel=out.getChannel();
			//lock方法的第三个参数设置为true则为共享锁，false则为排他锁
			FileLock lock=channel.lock(0, 30, false);
			ByteBuffer buff=ByteBuffer.wrap("啦啦啦啦，锁定了文件，可以无忧无虑的写数据了".getBytes());
			System.out.println("获得锁，先睡一分钟");
			Thread.sleep(60000);
			channel.write(buff);
			//在此期间，睡一分钟。
			System.out.println("算罢了，解锁了！");
			//解锁
			lock.release();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
/**
 *文件写入数据
 * @author Administrator
 *
 */
class WriteThread implements Runnable{
	String path;
	public WriteThread(String path){
		this.path=path;
	}
	public void run() {
		try {
			NIOExample.writeFile(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}