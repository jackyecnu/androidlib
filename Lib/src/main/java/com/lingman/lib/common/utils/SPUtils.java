package com.lingman.lib.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSON;
import com.lingman.base.app.BaseApplication;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class SPUtils
{
	public SPUtils()
	{
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * 保存在手机里面的文件名
	 */
	public static final String FILE_NAME = "share_data";

	/**
	 * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
	 *
	 * @param key
	 * @param object
	 */
	public static void put(String key, Object object)
	{

		SharedPreferences sp = BaseApplication.getAppContext().getSharedPreferences(FILE_NAME,	Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();

		if (object instanceof String)
		{
			editor.putString(key, (String) object);
		} else if (object instanceof Integer)
		{
			editor.putInt(key, (Integer) object);
		} else if (object instanceof Boolean)
		{
			editor.putBoolean(key, (Boolean) object);
		} else if (object instanceof Float)
		{
			editor.putFloat(key, (Float) object);
		} else if (object instanceof Long)
		{
			editor.putLong(key, (Long) object);
		} else
		{
			editor.putString(key, JSON.toJSONString(object));
		}
		SharedPreferencesCompat.apply(editor);
	}

	/**
	 * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
	 *
	 * @param key
	 * @param defaultObject
	 * @return
	 */
	public static Object get(String key, Object defaultObject)
	{
		SharedPreferences sp = BaseApplication.getAppContext().getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);

		if (defaultObject instanceof String)
		{
			return sp.getString(key, (String) defaultObject);
		} else if (defaultObject instanceof Integer)
		{
			return sp.getInt(key, (Integer) defaultObject);
		} else if (defaultObject instanceof Boolean)
		{
			return sp.getBoolean(key, (Boolean) defaultObject);
		} else if (defaultObject instanceof Float)
		{
			return sp.getFloat(key, (Float) defaultObject);
		} else if (defaultObject instanceof Long)
		{
			return sp.getLong(key, (Long) defaultObject);
		}
		return null;
	}

	public  static <T> T getObject(String key, Class clas){
		SharedPreferences sp = BaseApplication.getAppContext().getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		return  (T) JSON.parseObject(sp.getString(key,""),clas);
	}



	/**
	 * 移除某个key值已经对应的值
	 *
	 * @param key
	 */
	public static void remove( String key)
	{
		SharedPreferences sp = BaseApplication.getAppContext().getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.remove(key);
		SharedPreferencesCompat.apply(editor);
	}

	/**
	 * 清除所有数据
	 *
	 * @param
	 */
	public static void clear()
	{
		SharedPreferences sp = BaseApplication.getAppContext().getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.clear();
		SharedPreferencesCompat.apply(editor);
	}

	/**
	 * 查询某个key是否已经存在
	 *
	 * @param key
	 * @return
	 */
	public static boolean contains( String key)
	{
		SharedPreferences sp = BaseApplication.getAppContext().getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		return sp.contains(key);
	}

	/**
	 * 返回所有的键值对
	 *
	 * @return
	 */
	public static Map<String, ?> getAll()
	{
		SharedPreferences sp = BaseApplication.getAppContext().getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		return sp.getAll();
	}

	/**
	 * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
	 *
	 * @author zhy
	 *
	 */
	private static class SharedPreferencesCompat
	{
		private static final Method sApplyMethod = findApplyMethod();

		/**
		 * 反射查找apply的方法
		 *
		 * @return
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private static Method findApplyMethod()
		{
			try
			{
				Class clz = SharedPreferences.Editor.class;
				return clz.getMethod("apply");
			} catch (NoSuchMethodException e)
			{
			}

			return null;
		}

		/**
		 * 如果找到则使用apply执行，否则使用commit
		 *
		 * @param editor
		 */
		public static void apply(SharedPreferences.Editor editor)
		{
			try
			{
				if (sApplyMethod != null)
				{
					sApplyMethod.invoke(editor);
					return;
				}
			} catch (IllegalArgumentException e)
			{
			} catch (IllegalAccessException e)
			{
			} catch (InvocationTargetException e)
			{
			}
			editor.commit();
		}
	}
	/**
	 * 获取Map
	 * @param key
	 * @return
	 */
	public static Map<String,Object> getMap(String key){
		String object = getSharedPreferences().getString(key,"");
		if("".equals(object)){ return null;}
		return JSON.parseObject(object);
	}

	public static <T> T getBean(String key, Class<T> cls){
		String str = getSharedPreferences().getString(key,"");
		if("".equals(str) || !str.startsWith("{") || !str.endsWith("}")){ return null;}
		return JSON.parseObject(str,cls);
	}

	public static String getString(String key, String defValue){
		return getSharedPreferences().getString(key, defValue);
	}

	public static boolean getBoolean(String key, boolean defValue){
		return getSharedPreferences().getBoolean(key,defValue);
	}

	public static float getFloat(String key, float defValue){
		return getSharedPreferences().getFloat(key,defValue);
	}

	public static int getInt(String key, int defValue){
		return getSharedPreferences().getInt(key,defValue);
	}

//	public static void remove(Context context,String key){
//		Editor editor = getSharedPreferences(context).edit();
//		editor.remove(key);
//		editor.commit();
//	}

	private static SharedPreferences getSharedPreferences(){
		return BaseApplication.getAppContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
	}

//	public static boolean contains(Context context,String key){
//		return getSharedPreferences(context).contains(key);
//	}

}