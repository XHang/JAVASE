package com.cxh.vo;
import java.io.Serializable;


public class Person implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2622455358959289700L;
	private int age;
	String name;
	public Person(String name) {
		super();
		this.name = name;
	}
	public Person(int age,String name)
	{
		this.age=age;
		this.name=name;
	}
	public String toString()
	{
		return name+age;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
