package com.lingman.lib.network.http;


import com.norton.network.http.interceptor.HttpHeaderInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Norton on 2017/2/13.
 */

public class HttpHelper {

    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 10000;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 10000;
    public Retrofit retrofit;

    private static class Holder {
        private static HttpHelper mInstance = new HttpHelper();
    }

    //构造方法私有
    private HttpHelper() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(new HttpHeaderInterceptor()) //头部参数
                .addInterceptor(logInterceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Api.PRODUCT_URL)
                .build();
    }


    /**
     * @param
     */
    public static <T>T getDefault(Class<T> service) {
        HttpHelper retrofitManager = Holder.mInstance;
        if (Holder.mInstance == null) {
            retrofitManager = new HttpHelper();
        }
        return retrofitManager.retrofit.create(service);
    }

}
