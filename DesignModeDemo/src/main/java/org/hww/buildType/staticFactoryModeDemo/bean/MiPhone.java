package org.hww.buildType.staticFactoryModeDemo.bean;

import org.hww.buildType.staticFactoryModeDemo.iproduct.Phone;

public class MiPhone extends Phone {
    @Override
    public void start() {
        System.out.println("小米开机启动");
    }
}
