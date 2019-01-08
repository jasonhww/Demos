package org.hww;

//双重检查(DCL)
public class DSignleton {
    private static volatile DSignleton dSignleton = null;

    private DSignleton() {

    }

    public static DSignleton getInstance() {
        if (dSignleton == null) {
            synchronized (DSignleton.class) {
                if (dSignleton == null) {
                    dSignleton = new DSignleton();
                }
            }
        }
        return dSignleton;
    }

}
