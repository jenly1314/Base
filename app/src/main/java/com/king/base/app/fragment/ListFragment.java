package com.king.base.app.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.king.base.BaseFragment;
import com.king.base.adapter.HolderRecyclerAdapter;
import com.king.base.app.R;
import com.king.base.app.adapter.ListAdapter;
import com.king.view.superswiperefreshlayout.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public class ListFragment extends BaseFragment{

    private SuperSwipeRefreshLayout ssrl;

    private ListView listView;

    private ListAdapter adapter;

    private List<String> listData;


    public static ListFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initUI() {
        ssrl = findViewById(R.id.ssrl);
        listView = findViewById(R.id.listView);

        initListData();
        adapter = new ListAdapter(getContext(),listData);

        listView.setAdapter(adapter);

        ssrl.setOnRefreshListener(new SuperSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SuperSwipeRefreshLayout.Direction direction) {
                if(direction == SuperSwipeRefreshLayout.Direction.TOP){
                    pullRefresh();
                }else{
                    loadMoreRefresh();

                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast("position:" + position);
            }
        });
    }

    @Override
    public void initData() {

    }

    private void initListData(){
        if(listData == null){
            listData = new ArrayList<>();
        }else{
            listData.clear();
        }
        for (int i = 0; i < 20; i++) {
            listData.add("ListView Item" + i);
        }
    }

    private void refreshView(){
        adapter.notifyDataSetChanged();
        ssrl.setRefreshing(false);
    }

    private void pullRefresh(){
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                SystemClock.sleep(1000);
                return null;
            }

            @Override
            protected void onPostExecute(Void v) {
                super.onPostExecute(v);
                initListData();
                refreshView();
            }
        }.execute();
    }

    private void loadMoreRefresh(){

        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                SystemClock.sleep(1000);
                return null;
            }

            @Override
            protected void onPostExecute(Void v) {
                super.onPostExecute(v);
                listData.add("ListView Item" + listData.size());
                refreshView();
            }
        }.execute();
    }


    @Override
    public void addListeners() {


    }

    @Override
    public int inflaterRootView() {
        return R.layout.fragment_list;
    }
}
