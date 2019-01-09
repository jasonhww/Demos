package org.jasonhww.myandroidthreadstatusdemo;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.jasonhww.myandroidthreadstatusdemo.asynctaskDm.Robot;
import org.jasonhww.myandroidthreadstatusdemo.intentserviceDm.MyIntentService;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private ProgressDialog mProgressDialog;
    private TextView tvName;
    private Button btnCancel, btnDoAsync, btnDoIService, btnDoIHandlerThread;
    private MyAsyncTask mMyAsyncTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @SuppressLint("WrongViewCast")
    private void initView() {
        tvName = findViewById(R.id.tvName);
        btnCancel = findViewById(R.id.btnCancel);
        btnDoAsync = findViewById(R.id.btnDoAsync);
        btnDoIService = findViewById(R.id.btnDoIService);
        btnDoIHandlerThread = findViewById(R.id.btnDoIHandlerThread);
        initListener();
    }

    private void initListener() {
        btnCancel.setOnClickListener(this);
        btnDoAsync.setOnClickListener(this);
        btnDoIService.setOnClickListener(this);
        btnDoIHandlerThread.setOnClickListener(this);
    }

    private void doAsync() {
        mMyAsyncTask = new MyAsyncTask();
        mMyAsyncTask.execute("jasonhww");
    }

    /**
     * 使用HandlerThread
     */
    private void doHandlerThread() {
        HandlerThread handlerThread = new HandlerThread("handlerThread");
        handlerThread.start();
        final Handler handler = new Handler(handlerThread.getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Log.d(TAG, "handleMessage: " + Thread.currentThread().getName());
                return false;
            }
        });
        handler.sendEmptyMessage(0);
    }

    /**
     * 使用IntentService
     */
    private void doIntentService() {
        //连续开三个服务测试
        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("taskName", "org.jason.taskOne");
        startService(intent);
        intent.putExtra("taskName", "org.jason.taskTwo");
        startService(intent);
        intent.putExtra("taskName", "org.jason.taskThree");
        startService(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCancel:
                mMyAsyncTask.cancel(true);
                break;
            case R.id.btnDoAsync:
                doAsync();
                break;
            case R.id.btnDoIService:
                doIntentService();
                break;
            case R.id.btnDoIHandlerThread:
                doHandlerThread();
                break;
            default:
                break;
        }
    }

    class MyAsyncTask extends AsyncTask<String, Integer, Robot> {


        @Override
        protected void onPreExecute() {
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setMessage("正在加载");
            mProgressDialog.setMax(10);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.show();
        }

        @Override
        protected Robot doInBackground(String... strings) {

            Robot robot = null;
            if (strings != null && strings.length > 0) {
                for (int i = 0; i < 11; i++) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    publishProgress(i);
                    if (isCancelled()) {
                        break;
                    } else {
                        robot = new Robot("i", strings[0]);
                    }
                }
            } else {
                throw new IllegalArgumentException("please set the params");
            }

            return robot;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if (values != null && values.length > 0) {
                mProgressDialog.setProgress(values[0]);
            }
        }

        @Override
        protected void onPostExecute(Robot robot) {
            mProgressDialog.dismiss();
            tvName.setText(robot == null ? "参数不详" : robot.getName());
        }


        @Override
        protected void onCancelled() {
            tvName.setText("任务被取消");
        }
    }

}
