package com.cxh.lambdaExpression;

import java.util.Optional;

/**
 * 演示LambdaExpression的基本用法-替换匿名内部类
 * @author Administrator
 *
 */
public class LambdaExpressionBaseUsage {
	public static void main(String[] args) {
		lambdaFunctionQuote();
	}
	
	public void lambdaBaseExample(){
		//jdk8之前的创建线程方式
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("jdk8之前的创建线程----开启");
				
			}
		}).start();
		//使用LambdaExpression后的创建线程方式
		new Thread(() -> System.out.println("LambdaExpression创建线程----开启")).start();
		//在Lambda表达式中，编译器采取了一种叫做类型推断的技术，以此判断你写的Lambda表达式用的地方正不正确。
		//原理是，new Thread接受的参数是一个Runnable的对象，该对象只能实现一个run方法，该方法无参数，返回值为空，然后正好能对应你写的lambda表达式。
		//编译就能通过，当然实际没这么简单。
	}
	
	/**
	 * 方法引用示例
	 */
	public static void lambdaFunctionQuote(){
		
		//首先演示静态方法引用，请看下面的第二行演示，本来参数应传入lambda表达式，但是这次传入的是一个静态方法的引用
		//由此可见，静态方法的应用是格式是    类名：静态方法名
		Optional<String> op = Optional.ofNullable(null);
		String result = op.orElseGet(FunctionReferencesBean::staticFuntion);
		System.out.println("静态方法引用的结果是 ："+result);
		
		
		//接下来演示对象方法的引用，格式很简单，对象的名字::方法名
		FunctionReferencesBean bean  = new FunctionReferencesBean();
		String str = op.orElseGet(bean::objectMethod);
		System.out.println("方法引用的结果是 ："+str);
		
		
		//最后演示一下构造方法的引用，格式超级简单   类名::new  什么，害怕不知道引用的是哪一个构造函数？
		//忘了lambda的类型推断吗？
		BeanFactory<FunctionReferencesBean> beanFactory = FunctionReferencesBean::new;
		 beanFactory.createBean("我是剑", "我是指");
		
	}

}
