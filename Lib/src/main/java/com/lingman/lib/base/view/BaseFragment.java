package com.lingman.lib.base.view;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import io.reactivex.disposables.CompositeDisposable;

public abstract  class BaseFragment<VB extends ViewDataBinding> extends Fragment {
    protected BaseActivity mActivity;
    public CompositeDisposable mDisposables   = new CompositeDisposable(); //用于绑定Rxjava与activity的生命周期

    public VB mBinding;

    private ViewModelProvider mFragmentProvider;
    private ViewModelProvider mActivityProvider;
    private ViewModelProvider mApplicationProvider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        initView();
        handlerData();
        return mBinding.getRoot();
    }
    //获取布局文件
    protected abstract int getLayoutId();
    //初始化view
    protected abstract void initView();
    //初始回调数据
    protected abstract void handlerData();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity= (BaseActivity) context;
    }

    /*
     获取Fragment的ViewProvider
     */
    protected <T extends ViewModel> T getFragmentScopeViewModel(@NonNull Class<T> modelClass) {
        if (mFragmentProvider == null) {
            mFragmentProvider = new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory());
        }
        return mFragmentProvider.get(modelClass);
    }
    /*
         获取依附的Activity的ViewProvider
         */
    protected <T extends ViewModel> T getActivityScopeViewModel(@NonNull Class<T> modelClass) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(mActivity,new ViewModelProvider.NewInstanceFactory());
        }
        return mActivityProvider.get(modelClass);
    }
    /*
            获取全局的ViewProvider
            */
    protected <T extends ViewModel> T getApplicationScopeViewModel(@NonNull Class<T> modelClass) {
        if (mApplicationProvider == null) {
            mApplicationProvider = new ViewModelProvider(
                    (ViewModelStoreOwner) mActivity.getApplicationContext(), getApplicationFactory(mActivity));
        }
        return mApplicationProvider.get(modelClass);
    }

    private ViewModelProvider.Factory getApplicationFactory(Activity activity) {
        checkActivity(this);
        Application application = checkApplication(activity);
        return ViewModelProvider.AndroidViewModelFactory.getInstance(application);
    }

    private Application checkApplication(Activity activity) {
        Application application = activity.getApplication();
        if (application == null) {
            throw new IllegalStateException("Your activity/fragment is not yet attached to "
                    + "Application. You can't request ViewModel before onCreate call.");
        }
        return application;
    }

    private void checkActivity(Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            throw new IllegalStateException("Can't create ViewModelProvider for detached fragment");
        }
    }

    protected int getResColor(int color){
        return  getResources().getColor(color);
    }

    protected Drawable getResDra(int drawable){
        return  getResources().getDrawable(drawable);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposables != null && !mDisposables.isDisposed()) {
            mDisposables.dispose();
        }
    }
}
