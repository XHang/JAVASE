package com.cxh.lambdaExpression;

/**
 * 为了方便演示构造方法引用，特构造此类
 * 创造使用lambda表达式的环境，并使用构造方法引用。
 * @author Administrator
 *
 * @param <Bean>
 */
public interface BeanFactory<Bean extends FunctionReferencesBean>{
	Bean createBean(String key,String value);

}
