package com.king.base.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * @author Jenly
 * @date 2014-8-8
 */
public class SharedPreferencesUtils {
	
	public static final String PREF_NAME = "org.king.pref_name_jenly";
	
	public static SharedPreferences getSharedPreferences(Context context){
		return getSharedPreferences(context, PREF_NAME);
	}
	
	public static SharedPreferences getSharedPreferences(Context context, String prefName){
		return context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
	}
	
	//--------------------------Int
	public static void put(Context context,String key,int value){
		getSharedPreferences(context).edit().putInt(key, value).commit();
	}
	
	public static int getInt(Context context,String key,int defValue){
		return getSharedPreferences(context).getInt(key, defValue);
	}
	
	public static int getInt(Context context,String key){
		return getInt(context,key,0);
	}
	
	//--------------------------Float
	public static void put(Context context,String key,float value){
		getSharedPreferences(context).edit().putFloat(key, value).commit();
	}
	
	public static float getFloat(Context context,String key,float defValue){
		return getSharedPreferences(context).getFloat(key, defValue);
	}
	public static float getFloat(Context context,String key){
		return getFloat(context, key, 0);
	}
	
	//--------------------------Long
	public static void put(Context context,String key,long value){
		getSharedPreferences(context).edit().putLong(key, value).commit();
	}
	
	public static long getLong(Context context,String key,long defValue){
		return getSharedPreferences(context).getLong(key, defValue);
	}
	public static long getLong(Context context,String key){
		return getLong(context, key, 0);
	}
	
	//--------------------------Boolean
	public static void put(Context context,String key,boolean value){
		getSharedPreferences(context).edit().putBoolean(key, value).commit();
	}
	
	public static boolean getBoolean(Context context,String key,boolean defValue){
		return getSharedPreferences(context).getBoolean(key, defValue);
	}
	public static boolean getBoolean(Context context,String key){
		return getBoolean(context, key, false);
	}
	
	//--------------------------String
	public static void put(Context context,String key,String value){
		getSharedPreferences(context).edit().putString(key, value).commit();
	}
	
	public static String getString(Context context,String key,String defValue){
		return getSharedPreferences(context).getString(key, defValue);
	}
	
	public static String getString(Context context,String key){
		return getString(context, key, null);
	}
	
	//--------------------------Set<String>
	public static void put(Context context,String key,Set<String> value){
		getSharedPreferences(context).edit().putStringSet(key, value).commit();
	}
	
	public static Set<String> getStringSet(Context context,String key,Set<String> defValue){
		return getSharedPreferences(context).getStringSet(key, defValue);
	}
	
	public static Set<String> getStringSet(Context context,String key){
		return getStringSet(context, key, null);
	}
	
	//--------------------------Map
	@SuppressLint("CommitPrefEdits")
	public static void putMapString(Context context,Map<String,String> map){
		if(map!=null){
			SharedPreferences.Editor editor = getSharedPreferences(context).edit();
			
			for(Entry<String, String> entry: map.entrySet()){
				if(!TextUtils.isEmpty(entry.getKey()))
					editor.putString(entry.getKey(), entry.getValue());
			}
			
			editor.commit();
			
		}
	}
	
	//--------------------------Increase int
	/**
	 * 自增(默认自增1)
	 * @param context
	 * @param key
	 */
	public static void increase(Context context,String key){
		increase(context, key,1);
	}
	
	/**
	 * 自增
	 * @param context
	 * @param key
	 * @param deltaValue 增量值(基数)
	 */
	public static void increase(Context context,String key,int deltaValue){
		put(context,key,getInt(context, key)+deltaValue);
	}
	
}
