package com.Nio.Example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
/**
 * 利用NIO技术写入或者读取文件
 * 注意：为了简单，异常都没有怎么处理，实际使用需要在finally块释放资源
 * @author Administrator
 *
 */
public class NIOExample {
	/**
	 * 利用NIO技术写入文件.
	 * @param path 要写入的文件路径
	 * @throws IOException 
	 * 注意的几个事件：1：如果在缓冲区已经满了的情况下调用put方法，会爆错java.nio.BufferOverflowException
	 * 如果要往缓冲区写入大数据，就不得不在缓冲区满了情况下写入通道，然后再clean。
	 */
	public static  void  writeFile(String path) throws IOException{
		System.out.println("写入文件，文件路径为："+path);
		//第一步：根据FileOutputStream对象获取通道
		FileOutputStream out=new FileOutputStream(path);
		FileChannel  channel=out.getChannel();
		//第二步：获取一个缓冲区，其字节大小为1024
		ByteBuffer buff=ByteBuffer.allocate( 1024 );
		//第三步：往通道写点数据,更好的做法是在创建缓冲区时就指定这个字节数组。
		byte [] bytes=getText().getBytes("utf-8");
		int putSize=0;
		for(byte b:bytes){
			buff.put(b);
			putSize++;
			if(putSize>=1024){
				buff.flip();
				channel.write(buff);
				buff.clear();
			}
		}
		System.out.println("写入完毕");
		//第四步：将数据写入到通道中，无需告诉通道写入多少。特别注意：在数据写入通道之前，要调用一下缓冲区的flip方法，来准备写入
		out.close();
	}
	/**
	 * 利用NIo技术读取文件
	 * 几个技术问题：
	 * 1：缓冲的对象的clear并不是清空缓冲区，而是把内部的一个属性-指针归0，这样重新从通道读取的数据会覆盖旧数据。
	 * 		这样子，想想，如果旧数据有1024个字节，新读取进去的只有100字节，那么只有旧数据的100个字节被替换为新的数据，剩余的还是旧数据。
	 * 		你一读取，前面的是新的，后面全是旧的。这时候就要保留新读取进去是多少个字节。输出时就取前多少个字节
	 * 		另外，当新读取的字节数为-1时，表示已经读完。
	 * @param path 要读取的文件路径
	 * @throws Exception 
	 */
	public static void readFile(String path) throws Exception{
			//第一步：根据FileInputStream对象获取通道
				FileInputStream out=new FileInputStream(path);
				FileChannel  channel=out.getChannel();
				//第二步：获取一个缓冲区，其字节大小为1024
				ByteBuffer buff=ByteBuffer.allocate( 1024 );
				//第三步：将数据读取到通道中，可以发现，不需要告诉通道要读取多少数据到缓冲区中，会智能判断已经读了多少数据以及剩余还有多少空间可以读取
				//要读取缓冲区的数字时，调用一下flip，令其limit的大小为指针指向的位置，也就是我们存进缓冲区的大小。然后让指针指向0
				while(true){
					if((channel.read(buff)==-1)){
						break;
					}
					buff.flip();
					//第四步：从通道中读取数据，并显示出来
					System.out.println(new String (buff.array(),0,buff.limit(),"gbk"));
					buff.clear();
				}
				out.close();
	}
	public static void main(String[] args) throws Exception {
		readFile("d://1.txt");
	}


}
