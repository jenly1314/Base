/*
 Copyright © 2015, 2016 Jenly Yu <a href="mailto:jenly1314@gmail.com">Jenly</a>

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 	http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

 */
package com.king.base.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Jenly
 */
public class SharedPreferencesUtils {
	
	public static final String PREF_NAME = "com.king.pref_name_jenly";
	
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
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static void put(Context context, String key, Set<String> value){
		getSharedPreferences(context).edit().putStringSet(key, value).commit();
	}
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static Set<String> getStringSet(Context context, String key, Set<String> defValue){
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
