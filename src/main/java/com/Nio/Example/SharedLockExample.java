package com.Nio.Example;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 演示共享锁的使用
 * @author Administrator
 *
 */
public class SharedLockExample {
	public static void main(String[] args) {
		new Thread(new GetSharedLockAndWrite("d://1.txt")).start();
		//new Thread(new Write("d://1.txt")).start();
	}
	
}
/**
 * 获取共享锁并睡眠一段时间,睡眠结束后写数据，解锁，关闭资源。睡眠期间另一个线程开启，开始写数据。
 * 小疑问：不带参数的lock的锁是排它锁还是共享锁（结论，总是获取排他锁）
 * 小疑问：排它锁锁住了0-20个字节，那么另一个线程写入20以后的字节可以吗？(结论：可以)
 * 同理，共享锁锁住了0-20个字节，那么另一个线程写入20之后的字节行么？（结论：谁都别想写!）
 * 实验结论
 * 1：共享锁必须通过可读的通道才能获取到。
 * 2:  颠覆认知，加了共享锁，连加共享锁的那个线程都不能写入数据了。
 * 	  由此可见，加了共享锁，保证数据只能用于读，谁都不能修改（因为获取不了排它锁了）此外，据了解的情况，共享锁又叫读锁。
 * @author Administrator
 *
 */
class GetSharedLockAndWrite implements Runnable{
	String path;
	public GetSharedLockAndWrite(String path){
		this.path=path;
	}
	public void run() {
		try {
			System.out.println("共享锁程序启动，打开文件");
			RandomAccessFile randomAccess=new RandomAccessFile(path,"rw");
			FileChannel channel=randomAccess.getChannel();
			System.out.println("获得共享锁");
			FileLock lock=channel.lock();
			System.out.println("我看看锁是共享的吗？-----》"+lock.isShared());
			System.out.println("睡眠");
			Thread.sleep(60000);
			ByteBuffer buff=ByteBuffer.wrap("共享锁写入数据".getBytes());
			channel.write(buff);
			System.out.println("解除共享锁");
			lock.release();
			randomAccess.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
}
/**
 * 开启一个线程专门写入文件
 * @author Administrator
 *
 */
class Write implements Runnable{
	String path;
	public Write(String path){
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
