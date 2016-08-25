package com.king.base.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 通用ViewPagerFragmentAdapter
 * @author Jenly
 */
public class ViewPagerFragmentAdapter extends FragmentPagerAdapter{

    private List<Fragment> listData;

    private List<CharSequence> listTitle;

    public ViewPagerFragmentAdapter(FragmentManager fm, List<Fragment> listData){
        this(fm,listData,null);
    }

    public ViewPagerFragmentAdapter(FragmentManager fm, List<Fragment> listData, List<CharSequence> listTitle) {
        super(fm);
        this.listData = listData;
        this.listTitle = listTitle;
    }


    public List<Fragment> getListData() {
        return listData;
    }

    public void setListData(List<Fragment> listData) {
        this.listData = listData;
    }

    public List<CharSequence> getListTitle() {
        return listTitle;
    }

    public void setListTitle(List<CharSequence> listTitle) {
        this.listTitle = listTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return listData==null ? null : listData.get(position) ;
    }

    @Override
    public int getCount() {
        return listData == null ? 0 : listData.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if(listTitle!=null && listTitle.size()!=0){
            return listTitle.get(position);
        }
        return super.getPageTitle(position);
    }

}
