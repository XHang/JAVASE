package com.cxh.regex;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;

/*
 * 利用正则表达式对字符串进行检验测试
 */
public class UseRegexCheck {
	/**
	 * 用正则表达来测试字符串是否满足要求
	 * @throws IOException 
	 */
	@Test
	public void regexTest() throws IOException{
		System.out.println("请输入您的qq号");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		//读取键盘输入的qq号
		String qq=br.readLine();
		//定义正则表达式的规则:1长度为5-15,2：不能以0开头，3只能是数字
		//解释：[1-9]限制第一位数字不能以0开头。 [0-9]{4,14}匹配接下来的字符只能是数字，并且重复的次数大于4次，小于14次
		String regex="[1-9][0-9]{4,14}";
		//利用正则表达式判断qq号码的合法性
		boolean flag=qq.matches(regex);
		if(flag)
			System.out.println("合法QQ:"+qq);
		else
			System.out.println("您输入的qq号码有误！");
	}
	@Test
	public void checkMails() throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line=null;
		//更有用的正则表达式：[a-zA-Z0-9_]+\\@[a-zA-Z0-9]+(\\.[a-zA-Z]+){1,2}
		String regex="\\w+\\@\\w+(\\.\\w+)+";
		//获取正则对象
		Pattern p=Pattern.compile(regex);	
		System.out.println("请输入有关邮箱地址的字符串，该程序将把邮箱地址选出来");
		while((line=reader.readLine())!=null){
			//将正则对象和每一行的数据关联起来，并获取匹配器
			Matcher matcher=p.matcher(line);		
			//将该行的数据匹配，匹配成功，打印匹配的子串
			while(matcher.find())	{		
				System.out.print("匹配成功，你输入的邮箱地址是"+matcher.group());
			}
		}
	}
	
	
	/**
	 * 利用正则表达式替换
	 * 目标：把下面字符串里面的ip地址分开，并按照从小到大的顺序排开
	 * 小知识点：$1就是代指前面捕获域捕获到的字符串
	 */
	@Test
	public void replaceRegExpression(){
		String str="192.68.1.254 102.49.23.013 10.10.10.10 2.2.2.2 8.109.90.30";
		//前面是数字的都补两零（捕获括号里面匹配的字符，并往前面补两0）
		str=str.replaceAll("(\\d+)", "00$1");
		System.out.println("一轮替换的结果是"+str);
		//把前面0出现一次或多次，且0的后面恰好是3个数字的全替换成那三个数字
		str=str.replaceAll("0+(\\d{3})", "$1");	
		System.out.println("二轮替换的结果是"+str);
		//按空格分割
		String []arr=str.split("");			
		TreeSet <String> ts=new TreeSet <String> ();
		//把三个数组存到TreeSet集合中，由于之前做了处理，所以可以比较
		for(String i:arr){
			ts.add(i);
		}
		//最后一步，把上面为了比较而做的替换变化去掉
		for(String s:ts){
			//把前面0出现0次或者多次，0后面跟着1个数字及以上，替换成0后面的数字
			System.out.println(s.replaceAll("0*(\\d+)","$1"));		
		}											
	}

}
