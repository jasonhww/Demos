package org.jasonhww.eventbusdemo;

import android.app.Application;
import com.example.myapp.MyEventBusIndex;
import org.greenrobot.eventbus.EventBus;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
    }
}
