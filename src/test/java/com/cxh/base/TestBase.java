package com.cxh.base;

import org.junit.Test;

public class TestBase {
    /**
     * 演示如何取到外部类的值和内部类的值
     */
    @Test
    public void testExternalandInternal(){
        ExternalClass externalClass = new ExternalClass("this is internalValue");
        externalClass.externalValue = "this is externalValue";
        externalClass.showInternal();
        externalClass.internal.showExternal();
    }
}
