package org.hww.buildType.staticFactoryModeDemo.bean;

import org.hww.buildType.staticFactoryModeDemo.iproduct.Phone;

//实际产品对象
public class HonorPhone extends Phone {
    @Override
    public void start() {
        System.out.println("荣耀开机启动");
    }
}
