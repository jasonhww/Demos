package org.jasonhww.handlerdemo;

import android.annotation.SuppressLint;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private static final int what = 0;
    private SubThread mSubThread;
    private Button btnDoSubNoLooper,btnDoSub, btnDoMain;

    @SuppressLint("HandlerLeak")//会造成溢出,实际开发不能这样使用
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Log.d(TAG, "Callback.handleMessage: " + Thread.currentThread().getName());
            return false;
        }

    }) {
        @Override
        public void handleMessage(Message msg) {
            Log.d(TAG, "Handler.handleMessage: " + Thread.currentThread().getName());

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        btnDoSubNoLooper = findViewById(R.id.btnDoSubNoLooper);
        btnDoSub = findViewById(R.id.btnDoSub);
        btnDoMain = findViewById(R.id.btnDoMain);
    }

    private void initListener() {
        btnDoSubNoLooper.setOnClickListener(this);
        btnDoSub.setOnClickListener(this);
        btnDoMain.setOnClickListener(this);
    }

    /**
     * 使用自定义Thread使用Handler
     */
    private void doSub() {
        mSubThread = new SubThread("SubThread");
        mSubThread.start();
        //handler发送阶段
        Handler handler2 = new Handler(mSubThread.getLooper(), new Handler.Callback() {

            //handler处理阶段
            @Override
            public boolean handleMessage(Message msg) {
                Log.d(TAG, "handleMessage: " + Thread.currentThread().getName());
                return false;
            }
        });

        handler2.sendEmptyMessage(what);
    }

    /**
     * 在主线程中使用Handler
     */
    private void doMainSendMsg() {
        mHandler.sendEmptyMessage(what);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubThread.quitSafely();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDoSubNoLooper:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        new Handler();//直接报错因为没有looper.
                        //java.lang.RuntimeException: Can't create handler inside thread that has not called Looper.prepare()
                    }
                }).start();
                break;
            case R.id.btnDoSub:
                doSub();
                break;
            case R.id.btnDoMain:
                doMainSendMsg();
                break;
            default:
                break;
        }
    }
}
