package com.cxh.stream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * 分割文件
 * @author Mr-hang
 *
 */
public class SplitFile {
	public static void main(String[] args) throws IOException {
		splitFile(1024*1024*5,"1.mp3");//5m的缓存区，待分割的文件为1.MP3
	}
	/**
	 * 分割文件方法
	 * @param partSize 要分割的文件大小-字节数
	 * @param source   需要分割的源文件
	 * @throws IOException
	 */
	public static void splitFile(int partSize ,String source) throws IOException{
		//创建源文件的字节输入流对象
		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(source));
		//定义缓冲区
		byte []b=new byte[partSize];														
		int leng;
		BufferedOutputStream bos=null;
		int count=0;
		while((leng=bis.read(b))!=-1){
			bos=new BufferedOutputStream(new FileOutputStream((count++)+".part"));		//创建若干个文件输出流对象
			bos.write(b, 0, leng);														//将缓冲区内容写入文件输入流对象
			bos.close();																//保存并关闭写资源
		}
		bis.close();//关闭读资源
	}

}
