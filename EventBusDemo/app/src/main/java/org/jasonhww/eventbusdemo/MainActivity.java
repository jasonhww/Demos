package org.jasonhww.eventbusdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_main);
    }

    /**
     * 订阅方法1
     * @param dataEvent //事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN, priority = 0)
    public void onDataEventOne(Integer dataEvent) {
        Log.d(TAG, "onDataEventOne: " + dataEvent);
    }

    /**
     * 订阅方法2
     * @param dataEvent //事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN, priority = 1)
    public void onDataEventTwo(String dataEvent) {
    }

    @Override
    protected void onDestroy() {
        //反注册
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void btnSendOnclick(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
