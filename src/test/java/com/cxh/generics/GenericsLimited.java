package com.cxh.generics;

import java.util.Iterator;
import java.util.TreeSet;

import com.cxh.Comparator.Comp;
import com.cxh.vo.Person;
import com.cxh.vo.Student;
import com.cxh.vo.Worker;

public class GenericsLimited {
	public void genericsLimitedTest(){
		TreeSet <Student> St=new TreeSet <Student>(new Comp());
		TreeSet <Worker>  Wk=new TreeSet <Worker> (new Comp());
		St.add(new Student("学生小明01"));
		St.add(new Student("学生小明02"));
		St.add(new Student("学生小明03"));
		Wk.add(new Worker("小明的父亲01"));
		Wk.add(new Worker("小明的父亲02"));
		Wk.add(new Worker("小明的父亲03"));
		Iter(St);
		Iter(Wk);
	}
	//泛型上限，但不是定义泛型方法，而是参数泛型限定
	public static  void Iter(TreeSet <? extends Person>  ts){
		Iterator<? extends Person> it=ts.iterator ();
		while(it.hasNext())
			System.out.println(it.next().getName());
	}
}
