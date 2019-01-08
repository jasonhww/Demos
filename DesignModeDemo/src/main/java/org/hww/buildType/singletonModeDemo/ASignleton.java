package org.hww;

//饿汉式
public class ASignleton {

    private static final ASignleton aSignleton = new ASignleton();

    private ASignleton() {
    }

    public static ASignleton getInstance() {
        return aSignleton;
    }
}
