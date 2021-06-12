package com.lingman.lib.network.http.interceptor;

import android.os.Build;

import com.lingman.base.app.AppConfig;
import com.lingman.base.app.BaseApplication;
import com.lingman.common.utils.SPUtils;
import com.norton.network.http.Utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * okhttp添加http header拦截器
 */
public class HttpHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String agent = "Mozilla/5.0 (ShiLiuApp Android "+Utils.getVerName(BaseApplication.getAppContext())+" "+ Build.MANUFACTURER+")";
        Request.Builder builder = chain.request().newBuilder();
        Request requst = builder.addHeader("Content-Type", "application/json")
                .addHeader("token", SPUtils.getString(AppConfig.SPKey.TOKEN,""))
                .addHeader("Content-Type","application/json")
                .addHeader("shiliu-platform","Android")
                .addHeader("User-Agent",agent)
                .addHeader("shiliu-channel","Official")
                .addHeader("shiliu-device" , "")
                .addHeader("shiliu-version", Utils.getVerName(BaseApplication.getAppContext()))
                .build();
        return chain.proceed(requst);
    }
}
