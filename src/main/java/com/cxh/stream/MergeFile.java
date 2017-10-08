package com.cxh.stream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * 合并文件
 * @author Mr-hang
 *
 */
public class MergeFile{
	public static void main(String[] args) throws IOException {
		Merge("合并.mp3","0.part","1.part","2.part");
	}
	/**
	 * 合并文件方法
	 * @param target 合并后的文件位置
	 * @param file  需要合并的part文件
	 * @throws IOException
	 */
	public static void Merge(String target ,String ... file) throws IOException{
		//创建ArrayList高效集合
		ArrayList<InputStream> list=new ArrayList<InputStream>();
		//将输入流对象存到集合中
		for(String File:file)									
			list.add(new FileInputStream(File));
		final Iterator<InputStream> iterator=list.iterator();			//获取集合迭代器对象
		//创建枚举的子类对象，封装迭代器方法
		Enumeration<InputStream> enumeration=new Enumeration<InputStream>(){
			public boolean hasMoreElements(){
				return iterator.hasNext();
			}
			public InputStream nextElement(){
				return iterator.next();
			}
		};
		//创建合并流对象，传入一个枚举对象
		SequenceInputStream sequenceInputStream=new SequenceInputStream(enumeration);
		byte b[]=new byte[1024];
		int len=0;
		OutputStream os=new FileOutputStream(target);
		//写入合并流文件
		while((len=sequenceInputStream.read(b))!=-1){
			os.write(b, 0, len);
		}
		os.close();
		sequenceInputStream.close();
	}

}
