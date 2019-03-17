package com.cxh.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stringlearning {

    /**
     * 将指定字符插入到指定位置
     * @param srcString
     * @param index
     * @param insertString
     * @return
     */
    public static String  insertStr(String srcString,int index,String insertString){
        StringBuilder sb = new StringBuilder(srcString);
        return sb.insert(index,insertString).toString();
    }
    /**
     * 将指定字符插入到指定位置
     * @param srcString
     * @param index
     * @param insertString
     * @return
     */
    public static String  insertStr1(String srcString,int index,String insertString){
        List<String> list = new ArrayList<>(Arrays.asList(srcString.split("")));
        list.add(index,insertString);
        return String.join("",list);
    }

}
