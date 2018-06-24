package com.cxh.constant;

import java.util.HashMap;
import java.util.Map;

public class NumberConstant {

    /**
     * 可以将阿拉伯数字转成中文数字
     */
    public static final Map<Integer,String> NUMBER_MAP = new HashMap<Integer,String> ();
    {
        NUMBER_MAP.put(1,"一");
        NUMBER_MAP.put(2,"二");
        NUMBER_MAP.put(3,"三");
        NUMBER_MAP.put(4,"四");
        NUMBER_MAP.put(5,"五");
        NUMBER_MAP.put(6,"六");
        NUMBER_MAP.put(7,"七");
        NUMBER_MAP.put(8,"八");
        NUMBER_MAP.put(9,"九");
        NUMBER_MAP.put(10,"十");
    }
}
