package com.cxh.collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 演示快速失败的一个场景
 * 1. 一个线程遍历集合
 * 2. 一个线程修改集合
 * 这种情况就有可能使集合的failfast产生
 * 从而抛出ConcurrentModificationException
 * 为了能在不同的线程执行遍历和修改集合
 * 可以采取的解决方案有
 * 1. 所有涉及到修改集合的操作都变成同步代码
 * 2. 不使用ArrayList，而使用CopyOnWriteArrayList
 *ps:不管是哪种方法，遍历一开始遍历的数据都是固定的了，即使在遍历过程中，添加新数据，也不会遍历出来
 */
public class FailFastTest {

    public List<String> list ;

    final int originalSize = 8;

    @Before
    public void init(){
        list = new CopyOnWriteArrayList();
        for (int i = 0; i < originalSize; i++) {
            list.add(i+"");
        }
    }

    @Test
    public void testFailFast(){
        Thread thread1 =new Thread(this::traversing);
        Thread thread2 = new Thread(this::modifyList);
        thread1.start();
        thread2.start();

    }
    public void traversing(){
        for (String s : list) {
            System.out.println("traversing get:["+s+"]");
        }
    }
    public void modifyList(){
        for (int i = 0; i < 10; i++) {
            list.add((originalSize+i)+"");
            System.out.println("already add ["+originalSize+i+"]");
        }
    }
}
