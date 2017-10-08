package com.cxh.stream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/*
 * 演示一下字节数组的流对象
 */
public class ByteArrayStream {
	public static void main(String[] args){
		//创建一个字节数组输出流，传入一个源字节数组
		ByteArrayInputStream bis=new ByteArrayInputStream("这是一个源字节数据".getBytes());
		//创建一个字节数组输入流，用于将字节数据写入内部的字节数组中
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		int by=0;
		while((by=bis.read())!=-1)
			bos.write(by);
		//把字节数组输入流里面写入的数据打印出来。
		System.out.println(bos.toString());
	}

}
