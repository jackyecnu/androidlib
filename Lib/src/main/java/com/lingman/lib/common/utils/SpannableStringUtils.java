package com.lingman.lib.common.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.TypefaceSpan;

/**
 * Created by Android Studio.
 * User: Norton
 * Date: 2021/5/3
 * Time: 6:47 PM
 */
public class SpannableStringUtils {
    /**
     * 设置字体大小，用sp
     *
     * @param str     目标字符串
     * @param start   开始位置
     * @param end     结束位置
     * @param spSize  sp大小
     * @return
     */

    public static SpannableString getSizeSpanSpToPx( String str, int start, int end, int spSize) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new AbsoluteSizeSpan(SizeUtils.sp2px( spSize)), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置背景色
     *
     * @param str     目标字符串
     * @param start   开始位置
     * @param end     结束位置
     * @param color   颜色值 如：#CCCCCC
     * @return
     */

    public static SpannableString getBackGroundColorSpan( String str, int start, int end, int color) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new BackgroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;

    }

    /**
     * 设置文字颜色
     *
     * @param str     目标字符串
     * @param start   开始位置
     * @param end     结束位置
     * @param color   颜色值 如：#CCCCCC
     * @return
     */

    public static SpannableString getForegroundColorSpan( String str, int start, int end, int color) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return ss;

    }

    /**
     * 设置字体删除线
     *
     * @param str     目标字符串
     * @param start   开始位置
     * @param end     结束位置
     * @return
     */

    public SpannableString getDeleteLineSpan( String str, int start, int end) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new StrikethroughSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置放大系数
     *
     * @param context
     * @param str     目标字符串
     * @param start   开始位置
     * @param end     结束位置
     * @param scale   放大多少倍，x轴方向，y轴不变 如：0.5f， 2.0f
     * @return
     */

    public SpannableString getScaleSpan(Context context, String str, int start, int end, float scale) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new ScaleXSpan(scale), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置下标
     *
     * @param context
     * @param str     目标字符串
     * @param start   开始位置
     * @param end     结束位置
     * @return
     */

    public SpannableString getSubscriptSpan(Context context, String str, int start, int end) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new SubscriptSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 设置字体
     *
     * @param context
     * @param str      目标字符串
     * @param start    开始位置
     * @param end      结束位置
     * @param typeface 字体类型 如：default，efault-bold,monospace,serif,sans-serif
     * @return
     */

    public SpannableString getTypeFaceSpan(Context context, String str, int start, int end, String typeface) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new TypefaceSpan(typeface), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;

    }

    /**
     * 设置字体形体
     *
     * @param context
     * @param str     目标字符串
     * @param start   开始位置
     * @param end     结束位置
     * @param style   字体类型 如： Typeface.NORMAL正常 Typeface.BOLD粗体 Typeface.ITALIC斜体
     *                Typeface.BOLD_ITALIC粗斜体
     * @return
     */

    public SpannableString getStyleSpan(Context context, String str, int start, int end, int style) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new StyleSpan(style), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }
}