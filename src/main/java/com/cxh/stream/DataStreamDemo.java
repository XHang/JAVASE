package com.cxh.stream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 基本数据类型的IO流
 */
public class DataStreamDemo {
	public static void main(String[] args) throws IOException{
		 //writer();
		reader();
	}
	public static void writer() throws IOException{
		//创建基本数据类型的字节输出流，并和文件输出流关联
		DataOutputStream dos=new DataOutputStream(new FileOutputStream("Data.txt"));
		dos.writeInt(88);//写入int值
		dos.writeBoolean(true);
		dos.writeDouble(3.14555);
		dos.writeUTF("这是UTF-8修改版，一般转换流读不出来哈哈哈哈，哦，一个字节占8位哦");//写入UTF-8修改版的数据
		dos.close();
	}
	public static void reader() throws IOException{
		DataInputStream dis=new DataInputStream(new FileInputStream("Data.txt"));//创建基本数据类型的字节输入流，并和文件输入流关联
		int a=dis.readInt();	//读取第一个值，int
		boolean b=dis.readBoolean();
		double d=dis.readDouble();
		String UTF=dis.readUTF();
		System.out.println(a);
		System.out.println(b);
		System.out.println(d);
		System.out.println(UTF);
		dis.close();
	}
}
