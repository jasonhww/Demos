package org.hww.buildType.staticFactoryModeDemo.factory;

import org.hww.buildType.staticFactoryModeDemo.iproduct.Phone;
import org.hww.buildType.staticFactoryModeDemo.bean.HonorPhone;
import org.hww.buildType.staticFactoryModeDemo.bean.MiPhone;
import org.hww.buildType.staticFactoryModeDemo.bean.OppoPhone;

//静态工厂方法
public class PhoneStaticFactory {
    private static Phone phone = null;

    //根据不同参数类型生产对应产品
    public static Phone CreatePhone(int type) {
        switch (type) {
            case PhoneType.OPPO:
                phone = new OppoPhone();
                break;
            case PhoneType.MI:
                phone = new MiPhone();
                break;
            case PhoneType.HONOR:
                phone = new HonorPhone();
                break;

        }
        return phone;
    }
}
