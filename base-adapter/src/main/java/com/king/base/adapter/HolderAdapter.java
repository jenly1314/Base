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
package com.king.base.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * * 通用适配器（适合一些常规的适配器）
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 *
 * @param <T>
 *           实体对象
 * @param <H>
 *     		ViewHolder
 */
public abstract class HolderAdapter<T,H> extends AbstractAdapter<T>{

	public HolderAdapter(Context context, List<T> listData) {
		super(context, listData);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		H holder;
		T item = getListData().get(position);
		if(convertView==null){
			convertView = buildConvertView(getLayoutInflater(),item,position,parent);
			holder = buildHolder(convertView,item,position);

			convertView.setTag(holder);
		}else{
			holder = (H)convertView.getTag();
		}
		bindViewDatas(holder,item,position);

		return convertView;
	}

	/**
	 * 建立convertView
	 * @param layoutInflater
	 * @return
	 */
	public abstract View buildConvertView(LayoutInflater layoutInflater,T item,int position, ViewGroup parent);

	/**
	 * 建立视图Holder
	 * @param convertView
	 * @return
	 */
	public abstract H buildHolder(View convertView,T item,int position);

	/**
	 * 绑定数据
	 * @param holder
	 * @param item
	 * @param position
	 */
	public abstract void bindViewDatas(H holder,T item,int position);


}