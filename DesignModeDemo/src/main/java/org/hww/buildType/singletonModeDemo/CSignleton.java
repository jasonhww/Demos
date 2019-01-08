package org.hww;

//懒汉式(线程安全)
public class CSignleton {
    private static CSignleton cSignleton = null;

    private CSignleton() {

    }

    public static synchronized CSignleton getInstance() {
        if (cSignleton == null) {
            cSignleton = new CSignleton();
        }
        return cSignleton;
    }

}
