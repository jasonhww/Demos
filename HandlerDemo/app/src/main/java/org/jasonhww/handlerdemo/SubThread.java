package org.jasonhww.handlerdemo;

import android.os.Looper;

/**
 * 自定义具有消息循环的Thread
 * 这样就可以在此线程使用Handler了
 */
public class SubThread extends Thread {

    private Looper mLooper;

    public SubThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        //handler准备阶段
        Looper.prepare();
        synchronized (this) {
            mLooper = Looper.myLooper();
            //Looper初始化完成,则唤醒阻塞的
            notifyAll();
        }
        Looper.loop();
    }

    public Looper getLooper() {
        if (!isAlive()) {
            return null;
        }
        //当线程已经启动,但是Looper还未初始化完成时,则进入阻塞等待.
        synchronized (this) {
            while (isAlive() && mLooper == null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return mLooper;
    }


    public void quitSafely() {
        mLooper.quitSafely();
    }
}
