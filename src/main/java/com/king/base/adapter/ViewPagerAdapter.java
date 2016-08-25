package com.king.base.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

/**
 * 通用ViewPagerAdapter
 * @author Jenly
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
