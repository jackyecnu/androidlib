package com.lingman.lib.base.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.lingman.lib.base.model.BaseModel;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Android Studio.
 * User: Norton
 * Date: 2021/4/13
 * Time: 7:11 PM
 */
public  abstract  class BaseViewModel <M extends BaseModel> extends ViewModel  {
    protected M model;
    public abstract M createModel();

    public CompositeDisposable mDisposables;

    //一个BaseViewModel默认提供6个状态回调，
    public MutableLiveData<Boolean> res1 = new MutableLiveData<>();
    public MutableLiveData<Boolean> res2  = new MutableLiveData<>();
    public MutableLiveData<Boolean> res3  = new MutableLiveData<>();
    public MutableLiveData<Boolean> res4  = new MutableLiveData<>();
    public MutableLiveData<Boolean> res5  = new MutableLiveData<>();
    public MutableLiveData<Boolean> res6  = new MutableLiveData<>();
    public MutableLiveData<Boolean> res7  = new MutableLiveData<>();


    public MutableLiveData<Boolean> getRes1() {
        return res1;
    }
    public MutableLiveData<Boolean> getRes2() {
        return res2;
    }
    public MutableLiveData<Boolean> getRes3() {
        return res3;
    }
    public MutableLiveData<Boolean> getRes4() {
        return res4;
    }
    public MutableLiveData<Boolean> getRes5() {
        return res5;
    }
    public MutableLiveData<Boolean> getRes6() {
        return res6;
    }
    public MutableLiveData<Boolean> getRes7() {
        return res7;
    }


    public BaseViewModel() {
        this.model = createModel();
    }

    public void cancel() {
        if (mDisposables != null && !mDisposables.isDisposed()) {
            mDisposables.dispose();
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        cancel();
    }
}