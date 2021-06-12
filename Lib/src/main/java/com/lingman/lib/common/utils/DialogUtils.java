package com.lingman.lib.common.utils;

import android.content.Context;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.LoadingPopupView;

/**
 * Created by Android Studio.
 * User: Norton
 * Date: 2021/4/19
 * Time: 8:32 PM
 */
public class DialogUtils {
    public static LoadingPopupView loadingPopup;


    public static void loading(Context context){
        loading(context,"加载中...");
    }

    public static void loading(Context context, String title){
        if (loadingPopup == null) {
            loadingPopup =  new XPopup.Builder(context)
                    .asLoading(title);
        }
        loadingPopup.show();
    }

    public static void dismissLoading(){
        loadingPopup.dismiss();
    }
} 