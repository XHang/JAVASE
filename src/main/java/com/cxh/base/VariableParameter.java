package com.cxh.base;


public class VariableParameter {

    public static  void showVariableParameterFeature(int a,String  ... arr){
        System.out.println("接受到的a的值是【"+a+"】");
        //String.format里面的占位符和可变参数的类型必须匹配
        System.out.println(String.format("接受到的数组是【%s】",arr));
    }
}
