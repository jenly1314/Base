package com.king.base.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;

/**
 * 抽象适配器（实现部分父类方法、免去一些通用的代码）
 * @author Jenly
 *
 * @param <T>
 */
public abstract class AbstractAdapter<T> extends BaseAdapter{
	
	protected Context context;
	
	protected List<T> listData;
	
	protected LayoutInflater layoutInflater;
	
	public AbstractAdapter(Context context,List<T> listData){
		this.context = context;
		this.listData = listData;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return listData==null ? 0:listData.size();
	}

	@Override
	public Object getItem(int position) {
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
	
	
	public View inflate(int layoutId){
		return layoutInflater.inflate(layoutId, null);
	}

}
