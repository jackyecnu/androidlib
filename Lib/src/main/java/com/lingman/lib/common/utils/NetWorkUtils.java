package com.lingman.lib.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * 网络链接状态类
 */
public class NetWorkUtils {
	public static final int NETWORK_NONE = 0; // 没有网络连接
	public static final int NETWORK_WIFI = 1; // wifi连接
	public static final int NETWORK_2G = 2; // 2G
	public static final int NETWORK_3G = 3; // 3G
	public static final int NETWORK_4G = 4; // 4G
	public static final int NETWORK_MOBILE = 5; // 手机流量



	public static boolean isNetworkAvailable(Context activity) {
		Context context = activity.getApplicationContext();
		// 获取手机所有链接管理对象(包括对wi-fi,net等链接的管理)
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager == null) {
			return false;
		} else {
			// 获取NetWorkInfo对象
			@SuppressLint("MissingPermission") NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

			if (networkInfo != null && networkInfo.length > 0) {
				for (int i = 0; i < networkInfo.length; i++) {
					// 判断当前网络状态是否为链接状态
					if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}else{
						
					}
				}
			}

		}
		return false;
	}


	/**
	 * 获取运营商名字
	 *
	 * @param context context
	 * @return int
	 */
	public static String getOperatorName(Context context) {
		/*
		 * getSimOperatorName()就可以直接获取到运营商的名字
		 * 也可以使用IMSI获取，getSimOperator()，然后根据返回值判断，例如"46000"为移动
		 * IMSI相关链接：http://baike.baidu.com/item/imsi
		 */
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		// getSimOperatorName就可以直接获取到运营商的名字
		return telephonyManager.getSimOperatorName();
	}

	/**
	 * 获取当前网络连接的类型
	 *
	 * @param context context
	 * @return int
	 */
	public static int getNetworkState(Context context) {
		ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); // 获取网络服务
		if (null == connManager) { // 为空则认为无网络
			return NETWORK_NONE;
		}
		// 获取网络类型，如果为空，返回无网络
		NetworkInfo activeNetInfo = connManager.getActiveNetworkInfo();
		if (activeNetInfo == null || !activeNetInfo.isAvailable()) {
			return NETWORK_NONE;
		}
		// 判断是否为WIFI
		NetworkInfo wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (null != wifiInfo) {
			NetworkInfo.State state = wifiInfo.getState();
			if (null != state) {
				if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
					return NETWORK_WIFI;
				}
			}
		}
		// 若不是WIFI，则去判断是2G、3G、4G网
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		@SuppressLint("MissingPermission") int networkType = telephonyManager.getNetworkType();
		switch (networkType) {
			// 2G网络
			case TelephonyManager.NETWORK_TYPE_GPRS:
			case TelephonyManager.NETWORK_TYPE_CDMA:
			case TelephonyManager.NETWORK_TYPE_EDGE:
			case TelephonyManager.NETWORK_TYPE_1xRTT:
			case TelephonyManager.NETWORK_TYPE_IDEN:
				return NETWORK_2G;
			// 3G网络
			case TelephonyManager.NETWORK_TYPE_EVDO_A:
			case TelephonyManager.NETWORK_TYPE_UMTS:
			case TelephonyManager.NETWORK_TYPE_EVDO_0:
			case TelephonyManager.NETWORK_TYPE_HSDPA:
			case TelephonyManager.NETWORK_TYPE_HSUPA:
			case TelephonyManager.NETWORK_TYPE_HSPA:
			case TelephonyManager.NETWORK_TYPE_EVDO_B:
			case TelephonyManager.NETWORK_TYPE_EHRPD:
			case TelephonyManager.NETWORK_TYPE_HSPAP:
				return NETWORK_3G;
			// 4G网络
			case TelephonyManager.NETWORK_TYPE_LTE:
				return NETWORK_4G;
			default:
				return NETWORK_MOBILE;
		}
	}

	/**
	 * 判断网络是否连接
	 *
	 * @param context context
	 * @return true/false
	 */
	public static boolean isNetConnected(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null && info.isConnected()) {
				if (info.getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断是否wifi连接
	 *
	 * @param context context
	 * @return true/false
	 */
	public static synchronized boolean isWifiConnected(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager != null) {
			NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
			if (networkInfo != null) {
				int networkInfoType = networkInfo.getType();
				if (networkInfoType == ConnectivityManager.TYPE_WIFI || networkInfoType == ConnectivityManager.TYPE_ETHERNET) {
					return networkInfo.isConnected();
				}
			}
		}
		return false;
	}


}
