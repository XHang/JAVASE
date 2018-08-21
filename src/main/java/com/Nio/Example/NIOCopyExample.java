package com.Nio.Example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 利用NIO技术copy一个文件
 * @author Administrator
 *
 */
public class NIOCopyExample {
	public static void copyFile(String sourcePath,String targePath) throws Exception{
		FileInputStream in = new FileInputStream(sourcePath);
		FileOutputStream out = new FileOutputStream(targePath);
		FileChannel  inChannel=in.getChannel();
		FileChannel outChannel=out.getChannel();
		ByteBuffer buff=ByteBuffer.allocate(1024);
		int readSize=0;
		while(true){
			//从输入的通道中读取数据到缓冲区中，此过程中，指针会指向读取的字节数，同时还会返回该指针。
			readSize=inChannel.read(buff);
			if(readSize==-1){
				break;
			}
			//将limit指向指针，即填充进缓冲区的大小，然后将指针归0.
			buff.flip();
			outChannel.write(buff);
			//将limit重新设为缓冲区大小，并且指针再次归0
			buff.clear();
		}
		in.close();
		out.close();
	}
	public static void main(String[] args) throws  Exception{
		copyFile("D://1.txt","D://copy.txt");
	}
}
