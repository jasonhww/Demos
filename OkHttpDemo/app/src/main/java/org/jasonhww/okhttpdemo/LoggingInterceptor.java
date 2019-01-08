package org.jasonhww.okhttpdemo;

import android.util.Log;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Title:Android客户端
 * Description:
 * Copyright: Copyright (c) 2018
 * Company: 深圳市城际云科技开发有限公司
 * Author: LuHongkai
 * Job: Full Stack Engineer
 * Technosphere: C++/Java/Android/Ios/H5/Node.js/Python..
 * QQ or Phone: 1016563911 18566273598
 * Date: 18-11-13
 */


public class LoggingInterceptor implements Interceptor {
    private static final String TAG = "LoggingInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取请求
        Request request = chain.request();
        Log.d(TAG, "LoggingInterceptor: url = " + request.url() +
                "\nconnectionStatus = " + chain.connection() +
                "\nrequestHeaderInfo = " + request.headers());
        //执行请求
        Response response = chain.proceed(request);
        Log.d(TAG, "LoggingInterceptor: url = " + response.request() +
                "\nresponseHeaderInfo = " + response.headers());
        return response;
    }
}
