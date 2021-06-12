package com.lingman.lib.base.bean;

/**
 * Created by Android Studio. 该类用于ViewModel需要返回回调结果的时候使用
 * User: Norton
 * Date: 2021/4/19
 * Time: 6:26 PM
 */
public class BaseBean<T> {
    private BaseBeanState state;
    private T data;

    public BaseBeanState getState() {
        return state;
    }

    public void setState(BaseBeanState state) {
        this.state = state;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public  enum  BaseBeanState {
        SUCCESS,FAILED,LOADING
    }


}
