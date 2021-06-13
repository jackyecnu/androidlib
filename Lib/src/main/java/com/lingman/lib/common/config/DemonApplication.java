package com.lingman.lib.common.config;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.lingman.lib.BuildConfig;

import com.lingman.lib.R;
import com.lingman.lib.base.app.BaseApplication;
import com.lingman.lib.common.utils.LogUtil;
import com.lingman.lib.common.view.DefaultHead;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

/**
 * Created by Android Studio.
 * User: Norton
 * Date: 2021/4/11
 * Time: 7:35 PM
 */
public class DemonApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.logInit(BuildConfig.DEBUG);

        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.white, R.color.colorGray);//全局设置主题颜色
                return new DefaultHead(context).setDrawableSize(10).setTextSizeTitle(12);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });

        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.white, R.color.colorGray);//全局设置主题颜色
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(10).setTextSizeTitle(12);
            }
        });

    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}