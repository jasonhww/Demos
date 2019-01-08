package org.jasonhww.threaddemo.threadLocalDm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import org.jasonhww.threaddemo.R;

/**
 * ThreadLocal示例
 */
public class ThreadLocalActivity extends AppCompatActivity {
    private static final String TAG = "ThreadLocalActivity";
    //这里定义了一个ThreadLocal用来存储Integer类型的数据,设置了默认值为999.
    private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 999;
        }
    };

    public static void startActivity(Context pkgContext) {
        Intent intent = new Intent(pkgContext, ThreadLocalActivity.class);
        pkgContext.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threadlocal);
        threadLocal.set(100);
        new SubThreadA("SubThreadA").start();
        new SubThreadB("SubThreadB").start();
        System.out.println("MainThread:" + threadLocal.get());

    }

    class SubThreadA extends Thread {

        public SubThreadA(String name) {
            super(name);
        }

        @Override
        public void run() {
            threadLocal.set(110);

            int value = threadLocal.get();

            System.out.println("SubThreadA:" + value);
        }
    }

    class SubThreadB extends Thread {

        public SubThreadB(String name) {
            super(name);
        }

        @Override
        public void run() {

            int value = threadLocal.get();

            System.out.println("SubThreadB:" + value);
        }
    }

}
