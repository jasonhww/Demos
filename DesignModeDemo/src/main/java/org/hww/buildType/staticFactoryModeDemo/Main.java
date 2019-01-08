package org.hww.buildType.staticFactoryModeDemo;

import org.hww.buildType.staticFactoryModeDemo.factory.PhoneStaticFactory;
import org.hww.buildType.staticFactoryModeDemo.factory.PhoneType;

public class Main {
    public static void main(String[] args) {

        PhoneStaticFactory.CreatePhone(PhoneType.MI).start();
    }
}
