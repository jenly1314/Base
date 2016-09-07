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

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

/**
 * 通用ViewPagerAdapter
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 *
 */
public class ViewPagerAdapter extends PagerAdapter {

	private List<View> listViews = null;
	private List<CharSequence> listTitle = null;

	public ViewPagerAdapter(List<View> listViews) {
		this.listViews = listViews;
	}
	public ViewPagerAdapter(List<View> listViews,List<CharSequence> listTitle) {
		this.listViews = listViews;
		this.listTitle = listTitle;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(listViews.get(position));
	}
	
	@Override
	public int getCount() {
		return listViews==null ? 0:listViews.size();
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(listViews.get(position),0);
		return listViews.get(position);
	}

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

	public List<View> getListViews() {
		return listViews;
	}

	public void setListViews(List<View> listViews) {
		this.listViews = listViews;
	}
	
	
}
