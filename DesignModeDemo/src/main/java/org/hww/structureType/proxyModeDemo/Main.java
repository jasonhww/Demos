package org.hww;

import org.hww.dynamicProxy.DynamicProxyHouser;
import org.hww.staticProxy.IHouse;
import org.hww.staticProxy.ProxyHouser;
import org.hww.staticProxy.RealHouser;

import java.lang.reflect.Proxy;

public class Main {


    public static void main(String[] args) {
        System.out.println("--------静态代理------");
        RealHouser realHouser = new RealHouser();
        ProxyHouser proxyHouser = new ProxyHouser(realHouser);
        proxyHouser.contract();
        System.out.println("-------------------------------");
        System.out.println("--------动态代理-------");

        RealHouser realHouser1 = new RealHouser();
        //创建一个租房买房的动态代理类,该代理实现了IHouse接口
        /**
         * 第一个参数:真实对象的classLoader
         * 第二个参数:代理对象需要实现的接口class
         */
        IHouse iHouseProxy = (IHouse) Proxy.newProxyInstance(realHouser1.getClass().getClassLoader(),
                new Class[]{IHouse.class}, new DynamicProxyHouser(realHouser1));
        iHouseProxy.contract();
    }
}
