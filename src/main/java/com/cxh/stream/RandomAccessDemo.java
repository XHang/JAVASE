package com.cxh.stream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccess类的使用
 * @author Mr-hang
 *
 */
public class RandomAccessDemo {
	public static void main(String[] args) throws IOException{
		//InitFile();
		//modify_writer();
		Read();
	}
	public static void createFileasWriteAsData() throws IOException{
		//以可读可写方式，才能创建一个文件
		RandomAccessFile raf=new RandomAccessFile("Random.txt","rw");
		raf.write("老王".getBytes());
		raf.writeInt(23);
		raf.write("蛤蛤".getBytes());
		raf.writeInt(89);
		raf.close();
	}
	public static void modifyFileData() throws IOException{
		//以可读可写方式打开一个文件
		RandomAccessFile raf=new RandomAccessFile("Random.txt","rw");
		raf.seek(8);//设置指针为第八个字节
		raf.write("握草".getBytes());//改写第八个字节后的数据，即改写'蛤蛤'和'89'
		raf.write("55".getBytes());
		raf.close();
	}
	public static void Read() throws IOException{
		//以可读方式打开一个文件
		RandomAccessFile raf=new RandomAccessFile("Random.txt","rw");
		raf.seek(8);//设置指针为第八个字节，将从此字节开始读数据
		byte[]b=new byte[16];
		raf.read(b);//将第八个字节的数据读到字节数组中
		System.out.println(new String(b));
		raf.close();
	}
}
