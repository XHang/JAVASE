package com.cxh.base;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class DateLearnTest {

    DateLearn dateLearn = null;
    final String formatString = "yyyy-MM-dd HH:mm:ss";
    @Before
    public void init(){
        dateLearn = new DateLearn();
    }
    @Test
    public void dataFormat() {
        String str  = dateLearn.dataFormat(new Date(),formatString);
        System.out.println(String.format("现在时间是【%s】",str));
    }

    @Test
    public void stringToDate() {
        String srcDate = "2019-03-17 21:58:25";
        System.out.println(String.format("将字符串转成日期，结果是【%s】",dateLearn.stringToDate(srcDate,formatString)));
    }
}