package com.cxh.java8newfeature.inter;

import java.util.Optional;

/**
 * 演示java8新加的类：OptionalExample
 * @author Administrator
 *
 */
public class OptionalExample {
	
	public static void main(String[] args) {
		//1:实例化Optional对象,参数不可为null，否则报null指针异常，说好的可以容纳null的容器呢？摔！
		Optional<String> op1 = Optional.of("参数");
		
		//2 :实例化Optional对象,参数可为null
		Optional<String> op2 = Optional.ofNullable(null);
		
		
		//isPresent可以判断Optional里面的参数是null值否？非空true，空返回false；
		System.out.println("看下op2容器里面的那个参数是空的吗？-----"+!op2.isPresent());
		
		
		
		
		//get返回Optional容器里面的那个参数,参数值为null报 java.util.NoSuchElementException: No value present
		//恭喜，比nullPoint进步了一点点了
		System.out.println("op1容器里面的参数是什么？"+op1.get());
		
		
		
		//ifPresent函数接受的是一个Consumer接口类对象，且Optional有值的话，会执行里面的一个accept方法,否则忽略
		//特别指出的是，accept方法的参数就是躲在Optional容器的那个参数
		//PS:别用匿名内部类了，祭出lambda表达式
		op1.ifPresent((T) ->{System.out.println(T);});
		
		
		//orElse函数如果有值会返回该值，否则返回指定的值
		System.out.println(op2.orElse("这个Optional容器里面的参数没有值"));
		
		//orElseGet函数如果有值会返回该值，否则返回Supplier接口的实现生成的值。
		op2.orElseGet(() -> "我是返回值");
		
		
		//orElseThrow 函数如果有值的话返回值，如果没有则抛出Supplier接口对象的get方法后返回的对象
		//虽然和上面的例子一样都是Supplier接口，不同的是，这个方法规定Supplier接口对象的get方法只能返回Throwable的子类对象。
		op2.orElseThrow( () -> new NullPointerException("没有值"));
		
		//剩下的内容都是Optional和lambda表达式的结合体
		
		
		
		
		
		
		
		
		
		
	}
}
