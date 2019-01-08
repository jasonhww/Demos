package org.hww.buildType.factoryModeDemo.factory;

import org.hww.buildType.staticFactoryModeDemo.bean.HonorPhone;
import org.hww.buildType.staticFactoryModeDemo.bean.MiPhone;
import org.hww.buildType.staticFactoryModeDemo.bean.OppoPhone;
import org.hww.buildType.staticFactoryModeDemo.factory.PhoneStaticFactory;
import org.hww.buildType.staticFactoryModeDemo.factory.PhoneType;

public class Main {
    public static void main(String[] args) {
        //创建具体工厂
        AbstractPhoneFactory abstractPhoneFactory = new PhoneDynamicFactory();
        //生产对应产品
        OppoPhone oppoPhone = abstractPhoneFactory.createPhone(OppoPhone.class);
        HonorPhone honorPhone = abstractPhoneFactory.createPhone(HonorPhone.class);
        MiPhone miPhone = abstractPhoneFactory.createPhone(MiPhone.class);
        //启动
        oppoPhone.start();
        honorPhone.start();
        miPhone.start();
    }
}
