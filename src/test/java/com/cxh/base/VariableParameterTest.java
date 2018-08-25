package com.cxh.base;

import org.junit.Test;

import static org.junit.Assert.*;

public class VariableParameterTest {

    /**
     * 演示一个特别的可变参数的特性，就是如果可变参数作为最后一个参数。
     * 在调用的时候，可以不用传，对，连null都不用
     * 跟调用一个参数的方法一样
     * 我火星了？
     */
    @Test
    public void showVariableParameterFeature() {
        VariableParameter.showVariableParameterFeature(1);
    }
}