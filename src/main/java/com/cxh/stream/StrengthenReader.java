package com.cxh.stream;

import java.io.IOException;
import java.io.Reader;

/**
 * 增强reader类
 * 装饰类
 * @author Mr-hang
 *
 */
public class StrengthenReader  extends Reader{
	//定义数据读取流对象引用
	private Reader fr=null;
	//构造函数要实现多态，实现拓展，使之能传入父类的任意子类对象
	StrengthenReader(Reader fr){
		this.fr=fr;		
	}
	//增强功能，基于read()方法
	public String readline() throws IOException	{
		StringBuilder sb=new StringBuilder();//创建一个StringBuilder对象用于存储字符
		int ch;								 //存储每次读到的字符
		while((ch=fr.read())!=-1){
			if(ch=='\r')					//如果读到\r字符，则不存储	
				continue;
			if(ch=='\n')					//如果读到换行符，则返回字符串
				return sb.toString();
			sb.append((char)ch);			//存储字符到StringBuild里面
		}
		if(sb.length()!=0)					//防止最后一行没有换行符导致循环结束后直接输出null，故要多加判断
			return sb.toString();
		return null;						//表示数据已经读完并且本次调用不存储任何字符，则返回null
	}
	//覆盖的父类有抽象方法，需要调用子类已经覆盖好的方法
	public void close() throws IOException{	
		fr.close();
	}
	//覆盖的父类有抽象方法，需要调用子类已经覆盖好的方法
	public int read(char[] cbuf, int off, int len) throws IOException{ 
		return fr.read(cbuf, off, len);
	}
}
