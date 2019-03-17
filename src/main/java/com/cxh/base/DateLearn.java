package com.cxh.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateLearn {
    public String dataFormat(Date date,String formatString){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatString);
        return simpleDateFormat.format(date);
    }
    public Date stringToDate(String srcString,String formatString){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatString);
        try {
           return simpleDateFormat.parse(srcString);
        } catch (ParseException e) {
            throw new RuntimeException("字符串转时间失败"+e);
        }
    }



}
