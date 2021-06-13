package com.lingman.lib.base.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.multidex.MultiDex;

/**
 * Created by Android Studio.
 * User: Norton
 * Date: 2021/4/11
 * Time: 7:01 PM
 */
public class BaseApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        // 程序崩溃时触发线程  以下用来捕获程序崩溃异常
//        Thread.setDefaultUncaughtExceptionHandler(restartHandler);
    }

    public static Context getAppContext() {
        return context;
    }

    // 创建服务用于捕获崩溃异常
    private Thread.UncaughtExceptionHandler restartHandler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable ex) {
//            restartApp();//发生崩溃异常时,重启应用
        }
    };

    //重启App
    public void restartApp() {
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        android.os.Process.killProcess(android.os.Process.myPid());  //结束进程之前可以把你程序的注销或者退出代码放在这段代码之前
    }



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}