package org.hww;

//静态内部类
public class ESignleton {

    private ESignleton() {

    }

    public static ESignleton getInstance() {

        return ESignletonHolder.eSignleton;
    }

    public static class ESignletonHolder{
        private static final ESignleton eSignleton = new ESignleton();
    }
}
