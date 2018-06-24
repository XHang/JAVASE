package com.cxh.generics;


import com.cxh.constant.NumberConstant;
import com.cxh.vo.Student;

import java.util.ArrayList;
import java.util.List;

public class GenericsTest {

	/**
	 * 泛型类使用示例
	 */
	public void genericsClassTest(){
		GenericsClass<String> To=new GenericsClass<String>();
		To.SetObject(new String());
		String k=To.getObject();
	}
	
	public void genericsInterfaceTest(){
		new InterImpl().show("O(∩_∩O)哈哈哈~"); //调用泛型接口的子类方法无需指定引用数据类型
		new InterImpl <Integer>().show(4);//调用方法2的接口子类需要指定数据类型。
	}
	
	public void genericsMethodTest(){
		GenericsMethod.println("随便打印一个字符串");//直接用静态方法调用泛型。我并没有指定应该传入的是什么引用类型
		GenericsMethod d=new GenericsMethod();		//指定传入的参数是Person
		Integer o = d.get(new Integer(1));
	}

    /**
     * 演示泛型的一个小问题
     * 方法参数是迭代器对象，但是实际参数却可以是list集合
     * 然而实际上不可
     */
	public void genericsQuestionTest(){
	    List<Student> list = new ArrayList<Student>();
	    for(int i=0;i<9;i++){
	        Student student = new Student(NumberConstant.NUMBER_MAP.get(i));
            list.add(student);
        }
        Question question = new Question();
	    question.showList(list.iterator());
    }
}
