package org.jasonhww.myandroidthreadstatusdemo.intentserviceDm;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentService";

    public MyIntentService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate-->currentThread: "+Thread.currentThread().getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent-->currentThread: "+Thread.currentThread().getName());
        String taskName = intent.getStringExtra("taskName");
        Log.d(TAG, "taskName: " + taskName);
        SystemClock.sleep(2500);
        if ("org.jason.taskOne".equals(taskName)){
            Log.d(TAG, "do task: "+taskName);
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy-->currentThread: "+Thread.currentThread().getName());
        super.onDestroy();
    }
}
