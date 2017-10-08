package com.cxh.collections;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Test;

/**
 * 程序功能<br/>
 * 1.演示fill方法和reverse方法和replaceAll方法<br/>
 * 2.并写一个方法，使其能实现把集合的部分元素替换成元素
 */
public class CollectionDemo 
{
	/**
	 * 为测试程序组装数据
	 * @return
	 */
	private List  assemblyList(){
		ArrayList <String> list=new ArrayList <String> ();
		list.add("我是01号");
		list.add("我是05号");
		list.add("我是04号");
		list.add("我是02号");
		list.add("我是03号");
		list.add("我是07号");
		list.add("我是06号");
		return list;
	}
	
	/**
	 * replaceAll 将列list的一个指定值的所有出现替换为另一个<br/>
	 * reverse 将集合元素逆向排序
	 * fill 用指定元素替换列表的所有元素
	 * @param list  要操作的集合
	 */
	@Test
	public void fill_reverse_replaceAll(){
		List <String> list = assemblyList();
		System.out.println(list);//展示原集合
		Collections.replaceAll(list, "我是05号", "我没有号。哭~");
		System.out.println(list);//将指定的所有相同元素替换成指定元素的结果
		Collections.reverse(list);
		System.out.println(list);//翻转集合后的结果
		Collections.fill(list, "我们都是007号");
		System.out.println(list);//全部替换的结果
	}
	
	/**
	 * 可以将集合部分元素统一替换为其他元素。
	 * 《我们中出了一些叛徒》
	 * @param list  原集合
	 * @param Stare 开始替换的位置
	 * @param End	结束替换的位置
	 * @param key	替换后的元素
	 */
	//演示部分替换的方法。注意，java的习俗是包含头，不包含尾
	@Test
	public  void PartReplace(){
		List <String> list = assemblyList();
		int stare = 2;
		int end=4;
		String key = "FUCK";
		//其实很简单，就是遍历指定范围的集合，一个一个set
		for(int i=stare;i<=end;i++ ){
			list.set(i, key);
		}
		System.out.println("部分替换后的元素是"+list);
	}
	
	/**
	 * 演示反转比较器
	 */
	@Test
	public void reverseCompare(){
		//利用lambda创建转换器对象，比较策略：字符串长度大者为大，一样再比较字符串的字典顺序
		Comparator<String> comparator = (s1,s2)->{
			int num=new Integer(s1.length()).compareTo(new Integer(s2.length()));
			if (num==0)
				return s1.compareTo(s2);
			return num;
		};
		//正序比较器的set集合
		Set <String > positiveOrder=new TreeSet<String >(comparator);
		//反序比较器的Set集合
		Set <String > negativeOrder=new TreeSet<String >(Collections.reverseOrder(comparator));
		positiveOrder.add("01");
		positiveOrder.add("02");
		positiveOrder.add("003");
		positiveOrder.add("004");
		positiveOrder.add("05");
		
		negativeOrder.add("01");
		negativeOrder.add("02");
		negativeOrder.add("003");
		negativeOrder.add("004");
		negativeOrder.add("05");
		System.out.println("正转的的比较顺序"+positiveOrder);
		System.out.println("反转的的比较顺序"+negativeOrder);
	}
	
	/**
	 * 二分法查找jdk实现方式
	 */
	@Test
	public void binarySearch(){
		List list = assemblyList();
		Collections.sort(list);
		System.out.println("原集合排完序后的输出是"+list);
		String key ="我是05号";
		System.out.println(key+"在集合中的位置是"+Collections.binarySearch(list, key));
		key="我是*号";
		//如果binarySearch方法查找不到key，返回-1
		System.out.println(key+"在集合中的位置是"+Collections.binarySearch(list, key));
	}
	
	/**
	 * 这是一个自定义的二分法
	 */
	@Test
	public void customizeBinarySearchTest(){
		List list = assemblyList();
		Collections.sort(list);
		System.out.println("原集合排完序后的输出是"+list);
		String key ="我是05号";
		int index = customizeBinarySearch(list,key);
		System.out.println(key+"在集合中的位置是"+index);
	}
	private int customizeBinarySearch(List<String> list,String key ){
		int min=0;
		//max即集合最后一个元素的角标
		int max=list.size()-1;
		int middle;
		while(min<=max){
			middle=(min+max)/2;
			String middleStr = list.get(middle);
			int num=key.compareTo(middleStr);
			//compareTo如果返回值是正数说明调用者比参数要大
			if(num>0){
				min=middle;
			}else if(num<0){
				max=middle;
			}else{
				return middle;
			}
		}
		return -1;
	}
	

}
