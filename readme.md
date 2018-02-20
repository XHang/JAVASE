# 该项目试图整理我之前写的java基础
包含一下内容，欢迎补充
## 集合框架
1. 演示fill方法和reverse方法和replaceAll方法并实现一个部分替换的方法

## 反射
1. 演示最基础的方法

## JDBC
1. 批处理
2. DML语句
3. oracle的PLSQL语句
  附上PLSQL的程序代码
  <pre>
  create or replace procedure p
  (v_a in number,v_b in number,v_sum out number,v_temp in out number)
  is
  begin
  v_sum:=v_a+v_b;
  v_temp:=v_temp+1;
  end;
  /
  </pre>
4. 不定参数
5. 可以滚蛋的结果集
6. 事务处理
7. 结果集更新

## 字节流
注1：InputStream对象的available可以返回这个字节流中可以读取的字节数  

## 线程的并发问题
具体请查看测试代码里面的com.cxh.thread包，有一个ThreadExtendsImplTest
演示的几个方面的并发问题代码
1. 演示了线程的抢占性

2. 演示了售票程序，也就是线程并发问题。
  指的一提的是，这个线程并发问题解决的还不是完美。
  因为锁的判断要消耗一定的系统资源，所以还可以优化

3. 锁的优化在设计模式中的单例设计模式中。  
  关于单例模式的饿汉式和冷汉式  
  由于线程的并发，会产生一些问题  
  点此链接直接跳到设计模式
  ``

4. 特别说明的是，在同步代码块中（也就是被锁起来的代码中），如果线程wait了。

  那么与之相关的锁会被释放。不用担心在锁中睡眠时锁没有释放。

5. 顺便讲一下并发线程的锁类型。一般情况下，Synchronizer代码块里面需要自己设置锁对象

   其他的，成员方法加锁的话，锁对象是this。

   静态方法加锁的话，锁对象是Class对象

   以上，不想为这个知识点码代码了，各位记一下，脑子里有一个映像就行
