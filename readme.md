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
