package org.jasonhww.eventbusdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";
    private AppCompatButton mAppCompatButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_main);
        mAppCompatButton = (AppCompatButton) findViewById(R.id.btnSendEvent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, priority = 2)
    public void onDataEventTest(String dataEvent) {
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void btnSendOnclick(View view) {
        //发送整型类型的事件
        EventBus.getDefault().post(new Integer(802));
    }
}
