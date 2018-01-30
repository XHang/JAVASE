package com.cxh.Comparator;

import java.util.Comparator;

import com.cxh.vo.Person;
//泛型限定，Person为父类，对所有子类都能接受
public class Comp  implements Comparator <Person>		{
	public int compare(Person o1, Person o2) {
		 int num=o1.getName().compareTo(o2.getName());
		 return num;
	}
}