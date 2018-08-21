package com.Nio.Example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NIOBufferExample {
		/**
		 * 演示子缓冲区的作用，以及共享效果
		 * 其实就是修改了子缓冲区，其主缓冲区的被用于子缓冲区的数据也会同步改变，此谓之共享
		 */
		public static void sliceExample(){
			ByteBuffer buff=ByteBuffer.allocate(10);
			for(int index=0;index<buff.capacity();index++){
				buff.put((byte)index);
			}
			System.out.println("填充完毕的缓冲区为：");
			buff.flip();
			while(buff.remaining()>0){
				System.out.print(buff.get()+",");
			}
			buff.position(2);
			buff.limit(5);
			//子缓冲区的数据是根据缓冲区的指针作为起点，limit作为终点，截取缓冲区的数组。
			ByteBuffer sliceBuffer=buff.slice();
			//对子缓冲区的数据做点动作
			sliceBuffer.clear();
			for(int i=0;i<sliceBuffer.capacity();i++){
				byte b=sliceBuffer.get();
				b*=11;
				sliceBuffer.put(i,b);
			}
			System.out.println("修改了子缓冲区后的主缓冲区为：");
			buff.clear();
			while(buff.remaining()>0){
				System.out.print(buff.get()+",");
			}
		}
		/**
		 * 演示只读缓冲区的方法
		 * 特别注意：asReadOnlyBuffer虽然能将缓冲区变为只读，不过他是返回一个新的只读缓冲区，原来调用这个方法的缓冲区对象还是可写的。。。
		 */
		public static void onlyReadBuffer(){
			ByteBuffer buff=ByteBuffer.allocate(100);
			buff=buff.asReadOnlyBuffer();
			for(int i=0;i<buff.capacity();i++){
				buff.put((byte)i);
			}
			System.out.println("只读缓冲区写入成功。。。。。开玩笑，这句话不可能输出，必须报错，内容还必须是java.nio.ReadOnlyBufferException");
		}
		/**
		 * 演示从一个文件（通道）获取该文件的内存映射。
		 *  * 顺便温习一下RandomAccessFile，这对象可是既可写，又可读的。
		 * 另外，想从文件通道获取内存映射对象，其文件通道必须是从RandomAccessFile对象获取的
		 * 在操作之前，有一个疑问。是不是修改了内存映射对象，不做任何操作，其对应的文件数据也会随之改变呢？
		 * 宾果！
		 * 在进行试验时，发现对象只能是RandomAccessFile，否则会爆错。
		 * 怀疑是不是因为创建内存映射对象时选择的模式为READ_WRITE，导致其文件流对象也必须是可写的
		 * 为验证，请参考See Also:
		 * @see #memoryMap2
		 * 验证结果：符合猜想，创建内存映射对象时选择的模式要和创建文件流对象的类型对应，否则就会爆java.nio.channels.NonWritableChannelException
		 * 这是因为用Input流创建的通道只能是可读的，可读的通道不能创建可读可写的内存映射对象
		 * 
		 * 
		 * @throws Exception 
		 */
		public static void memoryMap(String path) throws Exception{
			RandomAccessFile randFile=new RandomAccessFile(path,"rw");
			FileChannel channel=randFile.getChannel();
			//创建文件的内存映射对象
			MappedByteBuffer  mapBuffer=channel.map(FileChannel.MapMode.READ_WRITE,0, 100);
			System.out.println("从内存映射读取出来的文件为：");
			while(mapBuffer.remaining()>0){
				System.out.print(mapBuffer.getChar());
			}
			randFile.close();
			mapBuffer.clear();
			mapBuffer.put("啦啦啦啦啦啦".getBytes());
			//不把缓冲区的数据写进通道，直接关闭随机流
			
		}
		/**
		 * 该方法将试图创建一个FileInputStream对象，并用该对象创建的通道创建一个内存映射对象，并尝试读取内容
		 * @param path
		 * @throws Exception
		 */
		public static void memoryMap2(String path) throws Exception{
			FileInputStream in=new FileInputStream(path);
			FileChannel channel=in.getChannel();
			//创建文件的内存映射对象
			MappedByteBuffer  mapBuffer=channel.map(FileChannel.MapMode.READ_WRITE,0, 100);
			System.out.println("从内存映射读取出来的文件为：");
			while(mapBuffer.remaining()>0){
				System.out.print(mapBuffer.getChar());
			}
			in.close();
		}
		public static void main(String[] args) throws Exception {
			memoryMap("d://1.txt");
		}
}
