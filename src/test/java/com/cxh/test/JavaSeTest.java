package com.cxh.test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Test;

/**
 * 用于补充一些javaSe时期的一些猜想测试程序
 * @author Mr-hang
 *
 */
public class JavaSeTest {
	
	/**
	 *  保留两位小数
	 */
	@Test
	public void retentionTwoDecimal(){
		double realNum = 12.851;
		//得到实数的整数部分
		double integer = Math.floor(realNum);
		System.out.println("实数"+realNum+"的整数部分是"+integer);
		double decimal=realNum-integer;//取出小数部分
		System.out.println("实数"+realNum+"的小数部分是"+decimal);
		double result=  ((Math.floor(decimal*100))*0.01)+integer;
		System.out.println(realNum+"保留两位小数的结果是"+result);
	}
	
	/**
	 * 编解码实例
	 * @throws UnsupportedEncodingException 
	 * 实际测试的时候不能达到效果，以前能的。
	 * 先暂且留个位置，日后再探究
	 */
	@Test
	public void codingAndDecoding() throws UnsupportedEncodingException{
		String s1="你好";
		byte[]b=s1.getBytes("GBK");//把字符串变成字节数组――编码
		System.out.println("gbk编码后的字节数组是"+Arrays.toString(b));//看GBK编码后字符串对应的字节。
		String s2=new String (b,"UTF-8");//解错码了，用iso解GBK码了
		System.out.println(s2);
		byte[] b1=s2.getBytes("UTF-8");//1：重新用ios编回字节码
		System.out.println(Arrays.toString(b1));//看编回的码是不是原来的字节码
		String s3=new String (b1,"GBK");  //2:用・GBK解码
		System.out.println(s3);
	}
}
