package org.hww;

//懒汉式(线程不安全)
public class BSignleton {
    private static BSignleton bSignleton = null;

    private BSignleton() {
    }

    public static BSignleton getInstance() {
        if (bSignleton == null) {
            bSignleton = new BSignleton();
        }
        return bSignleton;
    }



}
