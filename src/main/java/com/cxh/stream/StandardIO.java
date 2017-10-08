package com.cxh.stream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * 
 * @author Mr-hang
 *系统标准输入输出流的使用
 */
public class StandardIO{
	public static void main(String[] args) throws IOException {
		//读取键盘输入标准写法
		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		//创建写入打印流并与控制台相关联
		PrintWriter writer=new PrintWriter (System.out,true);
		String line=null;
		//读取键盘输入
		while((line=reader.readLine())!=null){
			if(line.equals("over"))
				break;
			writer.println(line.toUpperCase());//将从键盘中读取到的字符串转大写传到打印输出流中去
		}
		reader.close();//关闭资源
		writer.close();//关闭资源
	}

}
