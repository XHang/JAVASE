package com.cxh.base;

/**
 * 演示内部类_外部类
 * 保持代码简便，所以不加private修饰符和set/get方法。
 * 好孩子不要学
 */
public class ExternalClass {
    public String externalValue;
    //外部类要想访问到内部类，必须持有内部类的引用
    public Internal internal ;

    /**
     * 构造一个ExternalClass示例
     * @param internalValue 内部示例的值
     */
    public ExternalClass(String internalValue){
        internal = new Internal();
        internal.internalValue = internalValue;
    }
    class Internal{
        public  String internalValue;
        public void showExternal(){
            //内部类想访问内部类的引用直接使用。
            System.out.println("外部类的值是"+externalValue);
        }
    }
    public void showInternal(){
        System.out.println("内部类的值是"+this.internal.internalValue);

    }
}
