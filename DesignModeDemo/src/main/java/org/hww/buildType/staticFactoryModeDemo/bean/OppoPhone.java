package org.hww.buildType.staticFactoryModeDemo.bean;

import org.hww.buildType.staticFactoryModeDemo.iproduct.Phone;

public class OppoPhone extends Phone {
    @Override
    public void start() {
        System.out.println("OPPO开机启动");
    }
}
