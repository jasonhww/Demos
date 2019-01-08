package org.hww.buildType.factoryModeDemo.factory;

import org.hww.buildType.staticFactoryModeDemo.iproduct.Phone;

//具体工厂,通过反射生产不同产品
public class PhoneDynamicFactory extends AbstractPhoneFactory {


    @Override
    public <T extends Phone> T createPhone(Class<T> cla) {
        Phone phone = null;
        try {
            phone = (Phone) cla.forName(cla.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (T) phone;
    }
}
