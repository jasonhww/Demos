package org.jasonhww.threaddemo.synchronizedDm;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.jasonhww.threaddemo.synchronizedDm.Constants.count;
import static org.jasonhww.threaddemo.synchronizedDm.Constants.isRunning;

/**
 * 线程同步示例
 */
public class Pay {
    private static final String TAG = "Pay";
    private Lock mLock;
    private Condition mCondition;

    public Pay() {
        //初始化锁与条件对象
        this.mLock = new ReentrantLock();
        this.mCondition = mLock.newCondition();
    }

    /**
     * 未同步时
     */
    public void count() {
        if (count > 0) {
            System.out.println(Thread.currentThread().getName() + ":>" + count--);
        } else {
            isRunning = false;
        }
    }

    /**
     * 同步方法
     */
    public synchronized void countSyncMethod() {
        if (count > 0) {
            System.out.println(Thread.currentThread().getName() + ":>" + count--);
        } else {
            isRunning = false;
        }
    }

    /**
     * 同步代码块
     */
    public void countSyncCode() {
        synchronized (this) {
            if (count > 0) {
                System.out.println(Thread.currentThread().getName() + ":>" + count--);

            } else {
                isRunning = false;
            }
        }
    }

    /**
     * 重入锁
     */
    public void countSyncLk() {
        mLock.lock();
        try {
            if (count > 0) {
                System.out.println(Thread.currentThread().getName() + ":>" + count--);

            } else {
                isRunning = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mLock.unlock();
        }

    }

    public static void main(String[] args) {
        Constants.isRunning = true;
        Pay pay = new Pay();

        SyncThread syncThread1 = new SyncThread("线程1", pay);
        SyncThread syncThread2 = new SyncThread("线程2", pay);
        SyncThread syncThread3 = new SyncThread("线程3", pay);

        syncThread1.start();
        syncThread2.start();
        syncThread3.start();
    }
}
