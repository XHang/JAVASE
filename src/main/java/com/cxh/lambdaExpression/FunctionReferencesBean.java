package com.cxh.lambdaExpression;

/**
 * 方法引用的bena
 * 将存在静态方法，对象方法，构造器方法，并通过测试程序，用方法引用
 * @author Administrator
 *
 */
public class FunctionReferencesBean {

	public static String staticFuntion(){
		System.out.println("静态方法执行");
		return "真的是空指针，不骗人";
	}
	
	public FunctionReferencesBean(){
		
	}
	
	public  String   objectMethod(){
		System.out.println("对象方法执行");
		return "你可能得到了一个假的空指针";
	}
	public FunctionReferencesBean(String key,String value ){
		System.out.println("你创建了一个对象！");
		System.out.println("key :"+ key +"  value:"+value);
	}
	
	
}
