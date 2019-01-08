package org.jasonhww.okhttpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import okhttp3.*;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView idtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idtv = findViewById(R.id.idtv);
        syncGetRequest();
        //Log.d(TAG, "onCreate-Thread: " + Thread.currentThread());
        //asyncGetRequest();
        //asyncPostForm();
        //        asyncJson();
        //asyncCacheInterceptor();
    }

    //GET同步请求
    public void syncGetRequest() {
        String url = "http://api.k780.com/?app=weather.future&weaid=1&&appkey=10003" +
                "&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
        final OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = okHttpClient.newCall(request).execute();
                    Log.d(TAG, "syncGetRequest: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //GET异步请求
    public void asyncGetRequest() {
        String url = "http://api.k780.com/?app=weather.future&weaid=1&&appkey=10003" +
                "&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
        final OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "Callback-Thread: " + Thread.currentThread());
                String result = response.body().string();
                Log.d(TAG, "asyncGetRequest: " + result);
                //通过handler或者runOnUiThread方式切换线程.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        idtv.setText("jasonhww");
                    }
                });
                /*
                 *如果获取的是文件/图片
                 */
                //方式一:通过获取流
                //InputStream inputStream = response.body().byteStream();
                //方式二:通过获取字节数组
                //byte[] bytes = response.body().bytes();
            }
        });
    }

    //POST提交表单
    public void asyncPostForm() {
        String url = "http://api.k780.com/";
        final OkHttpClient okHttpClient = new OkHttpClient();
        //构建表单请求体
        RequestBody requestBody = new FormBody.Builder()
                .add("app", "weather.future")
                .add("weaid", "1")
                .add("appkey", "10003")
                .add("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4")
                .add("format", "json")
                .build();
        //创建POST请求
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "asyncPostForm: " + response.body().string());
            }
        });
    }

    //POST提交JSON
    public void asyncJson() {
        String url = "http://api.k780.com/?app=weather.future&weaid=1&&appkey=10003" +
                "&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
        String json = "{code:1,result:null}";
        OkHttpClient okHttpClient = new OkHttpClient();
        //指定媒体类型
        MediaType mediaType = MediaType.parse("application/json,charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "asyncJson: " + response.body().string());
            }
        });

    }

    //拦截器
    public void asyncCacheInterceptor() {
        String url = "http://api.k780.com/?app=weather.future&weaid=1&&appkey=10003" +
                "&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())//应用拦截
                .addNetworkInterceptor(new NetInterceptor())//网络拦截器
                .build();

        //通过拦截器修改请求头
        Request request = new Request.Builder()
                .get()
                .header("user-Agent","Interceptor example")
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "asyncCacheInterceptor: " + response.body().string());
            }
        });

    }
}
