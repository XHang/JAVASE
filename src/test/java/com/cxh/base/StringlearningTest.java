package com.cxh.base;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringlearningTest {

    @Test
    public void insertStr() {
        String srcString = "我的叫罪目";
        String insert = "名字";
        int index = 2;
        String targerStr = Stringlearning.insertStr(srcString,index,insert);
        System.out.println("得到新的目标字符串是:"+targerStr);
    }

    @Test
    public void insertStr1() {
        String srcString = "我的叫罪目";
        String insert = "名字";
        int index = 2;
        String targerStr = Stringlearning.insertStr1(srcString,index,insert);
        System.out.println("得到新的目标字符串是:"+targerStr);
    }
}