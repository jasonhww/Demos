package org.hww.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 创建一个动态代理,根据不同类型实现不同代理,运行时通过反射来动态地生成代理对象,并决定代理谁。
 */

public class DynamicProxyHouser implements InvocationHandler {
    private Object house;//被代理类

    public DynamicProxyHouser(Object house) {
        this.house = house;
    }

    /**
     * @param proxy  真实对象
     * @param method 真实对象的某个方法
     * @param args   真实对象某个方法接受的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        before();
        Object result = method.invoke(house, args);
        after();
        return result;
    }


    private void before() {
        System.out.println("联系看房");
    }


    private void after() {
        System.out.println("收取佣金");
    }
}
