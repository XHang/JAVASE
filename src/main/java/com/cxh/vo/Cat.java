package com.cxh.vo;
import java.io.Serializable;


public class Cat implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6326860739846107388L;
	String maose;
	int leng;
	public Cat(String maose,int leng)
	{
		this.maose=maose;
		this.leng=leng;
	}
	public String toString()
	{
		return maose+leng;
	}

}
