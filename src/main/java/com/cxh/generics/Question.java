package com.cxh.generics;

import com.cxh.vo.Student;

import java.util.Iterator;

/**
 * 泛型使用的一些新奇的东西
 */
public class Question {
    /**
     * 使用迭代器对象作为参数。但是却可以直接传list参数进来
     * 泛型的一些小问题
     * @param iteratort
     */
    public void showList(Iterator<Student> iteratort){
        while(iteratort.hasNext()){
            System.out.println("当前学生的姓名是"+iteratort.next().getName());
        }
    }
}
