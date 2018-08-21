package com.Nio.Example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * 利用NIO技术，从文本读取字符，并写入到另一个文本文件里面
 * @author Administrator
 *
 */
public class NIOTextCopyExample {
	public static void textCopy(String sourcePath,String targePath) throws Exception{
		//创建一个utf-8的字符集
		Charset utfCharset = Charset.forName( "utf-8" );
		
		//利用字符集创建一个解码器
		CharsetDecoder decoder = utfCharset.newDecoder();
		
		//利用字符集创建一个编码器
		CharsetEncoder encoder = utfCharset.newEncoder();
		
		FileInputStream in=new FileInputStream(sourcePath);
		
		FileChannel channel = in.getChannel();
		
		ByteBuffer byteBuff = ByteBuffer.allocate(1024);
		System.out.println("输出文件内容");
		while((channel.read(byteBuff)!=-1)){
			CharBuffer charBuff=decoder.decode(byteBuff);
			System.out.println(String.valueOf(charBuff.array()));
			byteBuff.clear();
		}
		in.close();
	}
	public static void main(String[] args) throws Exception {
		textCopy("1.txt", null);
	}

}
