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

	@Test
	public void testSelectSort(){
		int [] arr = new int[]{3,4,1,5,7,8};
		bubble(arr);
		printlnArray(arr);
	}

	/**
	 * 选择排序，面试练习，小的先排前
	 * @param arr
	 */
	public void selectSort(int [] arr){
		for(int i=0;i<arr.length;i++){
			for(int j=i;j<arr.length;j++){
				if (arr[i]>arr[j]){
					swap(arr,i,j);
				}
			}
		}
	}
	private void swap(int [] arr,int i,int j){
		int temp = arr[i];
		arr[i] = arr [j];
		arr[j] = temp;
	}
	private void printlnArray(int[] arr){
		for (int i:arr){
			System.out.print(i);
		}
		System.out.println();
	}

	/**
	 * 冒泡程序，面试训练
	 * @param arr
	 */
	public void bubble(int [] arr){
		for(int i=0;i<arr.length;i++){
			for (int j=0;j<arr.length-i-1;j++){
				if(arr[j]<arr[j+1]){
					swap(arr,j,j+1);
				}
			}
		}
	}

	@Test
	public void testNumber(){
		long s1= 2147483648L;
		long s2= 2147483648L;
		int result = (int) (s1+s2);
		System.out.println(result);
	}

}
