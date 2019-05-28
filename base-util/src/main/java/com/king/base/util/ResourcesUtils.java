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

import android.content.Context;

/**
 * 资源文件工具类
 * @author Jenly
 */
public class ResourcesUtils {
	
	private static final String RES_ID = "id";
	private static final String RES_STRING = "string";
	private static final String RES_DRAWABLE = "drawable";
	private static final String RES_LAYOUT = "layout";
	private static final String RES_STYLE = "style";
	private static final String RES_COLOR = "color";
	private static final String RES_DIMEN = "dimen";
	private static final String RES_ANIM = "anim";
	private static final String RES_MENU = "menu";

	private ResourcesUtils(){
		throw new AssertionError();
	}
	
	/**
	 * 获取资源文件的id
	 * @param context
	 * @param resName
	 * @return
	 */
	public static int getId(Context context,String resName){
		return getResId(context,resName,RES_ID);
	}
	
	/**
	 * 获取资源文件string的id
	 * @param context
	 * @param resName
	 * @return
	 */
	public static int getStringId(Context context,String resName){
		return getResId(context,resName,RES_STRING);
	}
	
	/**
	 * 获取资源文件drawable的id
	 * @param context
	 * @param resName
	 * @return
	 */
	public static int getDrawableId(Context context,String resName){
		return getResId(context,resName,RES_DRAWABLE);
	}
	
	/**
	 * 获取资源文件layout的id
	 * @param context
	 * @param resName
	 * @return
	 */
	public static int getLayoutId(Context context,String resName){
		return getResId(context,resName,RES_LAYOUT);
	}
	
	/**
	 * 获取资源文件style的id
	 * @param context
	 * @param resName
	 * @return
	 */
	public static int getStyleId(Context context,String resName){
		return getResId(context,resName,RES_STYLE);
	}
	
	/**
	 * 获取资源文件color的id
	 * @param context
	 * @param resName
	 * @return
	 */
	public static int getColorId(Context context,String resName){
		return getResId(context,resName,RES_COLOR);
	}
	
	/**
	 * 获取资源文件dimen的id
	 * @param context
	 * @param resName
	 * @return
	 */
	public static int getDimenId(Context context,String resName){
		return getResId(context,resName,RES_DIMEN);
	}
	
	/**
	 * 获取资源文件ainm的id
	 * @param context
	 * @param resName
	 * @return
	 */
	public static int getAnimId(Context context,String resName){
		return getResId(context,resName,RES_ANIM);
	}
	
	/**
	 * 获取资源文件menu的id
	 * @param context
	 * @param resName
	 */
	public static int getMenuId(Context context,String resName){
		return getResId(context,resName,RES_MENU);
	}
	
	/**
	 * 获取资源文件ID
	 * @param context
	 * @param resName
	 * @param defType
	 * @return
	 */
	public static int getResId(Context context,String resName,String defType){
		return context.getResources().getIdentifier(resName, defType, context.getPackageName());
	}

}
