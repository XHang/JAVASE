package com.cxh.jdk7.feature;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * 该类旨在演示JDK7的Comprae引发的Sort问题
 * JDK7对于Comprae的实现，提出了三点要求
 * 1. sgn(compare(x, y)) == -sgn(compare(y, x))
 *      example：sgn(compare(3, 1)) == -sgn(compare(1, 3))
 *               sgn(1) == -sgn(-1))
 *               满足条件
 *               另外，科普一下，sgn是一个函数，当参数大于0时，返回1，当参数小于0时，返回-1，参数为0时返回0
 *
 * 2. ((compare(x, y)>0) && (compare(y, z)>0)) 意味着 compare(x, z)>0
 * 3. compare(x, y)==0 意味着 (compare(x, z))==sgn(compare(y, z)) 对于所有Z都生效
 * 如果其中一个条件不满足，在使用sort方法时，就会报错
 */
public class CompraeExample {
    int compraeIndex = 0;
    @Test
    public void run(){
        Integer[] array ={0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 2, 1, 0, 0, 0, 2, 30, 0, 3};
        System.out.println("总共"+array.length+"个元素");
        Arrays.sort(array,new IntegerComprae());
    }

    private void printArrays(ArrayList list){
        list.forEach((element)->{
            System.out.println(element);
        });
    }

    class IntegerComprae implements Comparator<Integer> {
        @Override
        public int compare(Integer number1, Integer number2) {
            compraeIndex++;
            System.out.println("比较了"+compraeIndex+"遍");
            if (number1 >number2){
                return 1;
                //不考虑到两个数相等的情况，违反了第一理论。理论上会报我们想要的异常
                //但实际上，由于TimeSort的实现机理，未必会报错哦
            }/*else if (number1 == number2){
            return 0;
        }*/else {
                return -1;
            }
        }
    }


}


