package com.cxh.lambdaExpression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * java.uitl.Stream类的示例程序
 * @author Administrator
 *
 */
public class StreamExample {
	public static void main(String[] args) {
		
		
		//首先演示串行的执行
		List<String> list = new ArrayList<String>();
		list.add("元素1");
		list.add("元素2");
		list.add("元素3");
		list.add("元素4");
		list.add("元素5");
		list.add("元素6");
		list.add("伪元素7");
		list.add("真。元素8");
		//java8拓展了集合框架，就是加了stream类似的方法，该方法用于获取Stram对象
		Stream<String> stream = list.stream();
		//首先来看看filter方法，该方法接受一个Predicate接口对象，需要覆盖boolean test(T t)方法
		//所以我们只需要构建一个lambda方法，接受String对象，返回布尔值即可
		//最后，这个方法返回一个Stream对象，里面包括filter后的结果，别告诉我你不知道filter的意思是什么？
		stream = stream.filter((str)  -> str.startsWith("元"));
		
		
		
		//接下来介绍的是这货forEach，顾名思义，他就是用来循环的，接受参数是一个Consumer接口的对象
		//该接口类需要实现一个方法void accept(T t);
		//刚好,我们的老熟人System.out.println(Object obj);就是这么一个方法，直接方法引用吧。
		//那么，下面的代码完成的功能就是：循环遍历stream流里面的元素，并将其打印出来
		stream.forEach(System.out::println);
		//总结，以上的程序，首先把首元素是‘元’的元素取出来，然后循环打印
		
		
		//接下来讲解Stream对象的Sorted方法，这方法有两个重载，一个不需要任何参数，根据自然顺序排序，一个接受比较器对象，用lambda表达式就搞定了
		//没技术含量，不讲
		
		
		//下面是stream对象的map函数，这个函数可以改变流里面的每一个元素.该函数接受一个Function接口的对象，该对象需要覆写R apply(T t);方法
		//toUpperCase完美的切合这个方法。但是toUpperCase实际是个成员方法。这种引用方式被称为任意对象的方法引用。前提是这个方法返回的是本类对象
		//stream.map(String::toUpperCase);
		//以上方法专门将stream流里面的字符串全都转成大写。
		
		//stream对象的Match，顾名思义，就是用于匹配的
		//1:anyMatch函数,这个函数又是接受一个redicate接口对象，需要覆盖boolean test(T t)，返回值是一个boolean
		stream = list.stream();
		boolean falg = stream.anyMatch((str) ->str.startsWith("真"));
		System.out.println("流中有首字母是 '你'的字符串吗？    "+falg);
		
		//2:allMatch函数，这个函数
		stream = list.stream();
		boolean flag = stream.allMatch((str) ->str.startsWith("元"));
		System.out.println("流中所有元素首字母都是元吗 "+flag);
		
		//3:noneMatch函数，不匹配的，就不写了
		
		//count函数直接计算个数，简单不解释
		
		//Reduce函数有多个重载方法，其中一个是接受BinaryOperator接口对象，该接口需要实现一个方法 R apply(T t, U u);
		//该函数最后返回值就一个元素，我猜测这个函数是用于合并元素。。原定义是：使用关联累加函数对该流的元素进行减少，并返回一个可选描述减小值
		stream = list.stream();
		stream.reduce((s1,s2) -> s1+"and"+s2).ifPresent(System.out::println);
		
		
		testMap();
		
		
	
		
		
	}
	
	public static void testparallel(){
		//用并发处理大集合数据！
		List<String> bigList = new ArrayList<String>(10000000);
		for(int i=0;i<10000000;i++){
			bigList.add(UUID.randomUUID().toString());
		}
		//首先排序并计算总数--使用串行
		long sequentialStareTime = System.nanoTime();
		long count = bigList.stream().sorted().count();
		System.out.println("总计数为："+count);
		System.out.println("串行执行纳秒数："+(System.nanoTime()-sequentialStareTime));
		
		//并行排序计算总数
		long parallelStareTime = System.nanoTime();
		long  num=bigList.parallelStream().sorted().count();
		System.out.println("总计数为："+num);
		System.out.println("并行执行纳秒数："+(System.nanoTime()-parallelStareTime));
		//快大概几十毫秒
	}
	
	public static void testMap(){
		//Map集合有没有提供像List集合的stream呢？没有，但是你可以用Map集合得到Set集合，这个集合就有stream对象了
		//或者用Map集合新增的几个接口方法
		//putIfAbsent 如果当前放置的key已存在value,且不为null，返回该key对应的值，否则放置，并返回null;
		//forEach就是循环，比起之前的循环，好太多了。
		Map <Integer,String > map =new HashMap<Integer,String>();
		for(int i=0;i<10;i++){
			map.putIfAbsent(i, "value"+i);
		}
		
		//computeIfPresent这个判断key是否存在且不为null，满足条件则调用lambda表达式的返回值作为该key对应的value。
		//如果lambda返回null，则该key会被删除
		map.computeIfPresent(3, (key,value) ->"new value");
		
		//computeIfAbsent如果指定的键尚未关联，则使用给定的lambda函数计算值，并put进去，当然lambda函数不能返回null
		map.computeIfAbsent(44, (num) -> "厉害了");
		
		
		
		//接下来是这个getOrDefault，第一个参数是key，如果该key有值，方法返回该值，如果key没有值。返回第二个参数的值。不解释
		
		
		//最后是merge.其含义是：
		//如果key无对应的值或者对应的值为null，则将第二个参数作为值put进去
		//如果key有了对应的值，，将第三个参数，lambda表达式的值put进去，如果表达式的值为null，kill it!
		map.merge(9, "new value9", StreamExample::function);
		map.merge(40, "value40", StreamExample::function);
		map.forEach((i,str) ->System.out.println("key:"+i+"    value:"+str));
	}
	public static String  function(String oldvalue,String newvalue){
		return oldvalue+newvalue;
	}
	
	
	
}
