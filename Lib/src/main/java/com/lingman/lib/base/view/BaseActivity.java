package com.lingman.lib.base.view;


import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import com.lingman.lib.base.utils.AppManager;

import io.reactivex.disposables.CompositeDisposable;

/**
 * 基类
 */

public abstract class BaseActivity<VB extends ViewDataBinding> extends AppCompatActivity {

    public CompositeDisposable mDisposables   = new CompositeDisposable(); //用于绑定Rxjava与activity的生命周期

    public VB mBinding;

    private ViewModelProvider mActivityProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforeSetcontentView();
        mBinding = DataBindingUtil.setContentView(this,getLayoutId());
        this.initView();
        handlerData();
    }

    /**
     * 设置layout前配置
     */
    public void doBeforeSetcontentView() {

        // 把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initImmersionBar();
    }

    /*
             获取ViewProvider
             */
    protected <T extends ViewModel> T getViewModel(@NonNull Class<T> modelClass) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory());
        }
        return mActivityProvider.get(modelClass);
    }


    /*********************子类实现*****************************/
    //获取布局文件
    public abstract int getLayoutId();


    //初始化view
    public abstract void initView();


    //处理Data数据回调
    public abstract void handlerData();



    /**
     * 初始化沉浸式
     * Init immersion bar.
     */
    protected void initImmersionBar() {
//        //设置共同沉浸式样式
//        ImmersionBar.with(this)
//                .statusBarDarkFont(true)
//                .statusBarView(R.id.top_view)
//                .statusBarColor(R.color.main_color)
//                .init();
    }
    /**
     * 得到Res下颜色
     *
     * @param color
     * @return
     */
    public int getResColor(int color) {
        return ContextCompat.getColor(getApplicationContext(), color);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mDisposables != null && !mDisposables.isDisposed()) {
            mDisposables.dispose();
        }

        AppManager.getAppManager().removeActivity(this);
    }
}
