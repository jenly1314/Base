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


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 抽象适配器（实现部分父类方法、免去一些通用的代码）
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 *
 * @param <T>
 *           实体对象
 */
public abstract class AbstractAdapter<T> extends BaseAdapter{
	
	private Context context;
	
	private List<T> listData;

	private LayoutInflater layoutInflater;
	
	public AbstractAdapter(Context context,List<T> listData){
		this.context = context;
        this.listData = listData == null ? new ArrayList<T>() : listData;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return listData==null ? 0:listData.size();
	}

	@Override
	public T getItem(int position) {
		return listData==null ? null:listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public List<T> getListData() {
		return listData;
	}

	public void setListData(List<T> listData) {
		this.listData = listData;
	}

	public Context getContext(){
		return context;
	}

	public LayoutInflater getLayoutInflater(){
		return layoutInflater;
	}
	
	public View inflate(@LayoutRes int layoutId,ViewGroup parent,boolean attachToRoot){
		return layoutInflater.inflate(layoutId,parent,attachToRoot);
	}

}
