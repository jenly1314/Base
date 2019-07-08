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
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

/**
 * ViewPager通用适配器
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @param <T>
 *           实体对象
 */
public abstract class ViewPagerAdapter<T> extends PagerAdapter {

	protected Context context;
	private List<T> listData = null;
	private List<CharSequence> listTitle = null;

	public ViewPagerAdapter(Context context, List<T> listData) {
		this.context = context;
		this.listData = listData;
	}
	public ViewPagerAdapter(Context context,List<T> listData,List<CharSequence> listTitle) {
		this.context = context;
		this.listData = listData;
		this.listTitle = listTitle;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View)object);
	}

	@Override
	public int getCount() {
		return listData==null ? 0:listData.size();
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = getView(container,listData.get(position),position);
		container.addView(view);
		return view;
	}

	public abstract View getView(ViewGroup container,T t,int position);

	@Override
	public boolean isViewFromObject(View paramView, Object paramObject) {
		return paramView == paramObject;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		if(listTitle!=null && listTitle.size()!=0){
			return listTitle.get(position);
		}
		return super.getPageTitle(position);
	}


}
