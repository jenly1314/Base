package com.king.base.app.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.king.base.BaseFragment;
import com.king.base.app.R;
import com.king.base.app.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public class ListFragment extends BaseFragment{

    private ListView listView;

    private ListAdapter listAdapter;

    private List<String> listData;


    public static ListFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initUI() {
        listView = findView(R.id.listView);
    }

    @Override
    public void initData() {
        listData = new ArrayList<>();
        for (int i = 0; i <50 ; i++) {
            listData.add("ListView Item" + i);
        }
        listAdapter = new ListAdapter(getContext(),listData);

        listView.setAdapter(listAdapter);
    }

    @Override
    public void addListeners() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast("position:" + position);
            }
        });

    }

    @Override
    public int inflaterRootView() {
        return R.layout.fragment_list;
    }
}
