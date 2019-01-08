package org.jasonhww.threaddemo.synchronizedDm;

import static org.jasonhww.threaddemo.synchronizedDm.Constants.isRunning;

public class SyncThread extends Thread {
    private static final String TAG = "SyncThread";
    private Pay pay;

    public SyncThread(String name, Pay pay) {
        super(name);
        this.pay = pay;

    }

    @Override
    public void run() {
        while (isRunning) {
            pay.count();
//            pay.countSyncMethod();
//            pay.countSyncCode();
//            pay.countSyncLk();
        }
    }
}
