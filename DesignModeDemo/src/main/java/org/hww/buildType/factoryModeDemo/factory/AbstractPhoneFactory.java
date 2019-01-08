package org.hww.buildType.factoryModeDemo.factory;

import org.hww.buildType.staticFactoryModeDemo.iproduct.Phone;

//定义一个抽象工厂
public abstract class AbstractPhoneFactory {

    public abstract <T extends Phone> T createPhone(Class<T> cla);

}
