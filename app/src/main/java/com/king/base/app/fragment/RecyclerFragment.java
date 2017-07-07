package com.king.base.app.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.king.base.BaseFragment;
import com.king.base.adapter.HolderRecyclerAdapter;
import com.king.base.adapter.divider.DividerItemDecoration;
import com.king.base.app.R;
import com.king.base.app.adapter.RecyclerViewAdapter;
import com.king.view.superswiperefreshlayout.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public class RecyclerFragment extends BaseFragment {

    private SuperSwipeRefreshLayout ssrl;

    private RecyclerView recyclerView;

    private RecyclerViewAdapter adapter;

    private List<String> listData;

    public static RecyclerFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RecyclerFragment fragment = new RecyclerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initUI() {
        ssrl = findView(R.id.ssrl);
        recyclerView = findView(R.id.recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
    }

    @Override
    public void initData() {

        listData = new ArrayList<>();
        initListData();
        adapter = new RecyclerViewAdapter(getContext(),listData);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
    }

    private void initListData(){
        if(listData == null){
            listData = new ArrayList<>();
        }else{
            listData.clear();
        }
        for (int i = 0; i < 20; i++) {
            listData.add("RecyclerView Item" + i);
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
                SystemClock.sleep(2000);
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
                SystemClock.sleep(2000);
                return null;
            }

            @Override
            protected void onPostExecute(Void v) {
                super.onPostExecute(v);
                listData.add("RecyclerView Item" + listData.size());
                refreshView();
            }
        }.execute();
    }


    @Override
    public void addListeners() {

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

        adapter.setOnItemClicklistener(new HolderRecyclerAdapter.OnItemClicklistener() {
            @Override
            public void onItemClick(View v, int position) {
                    showToast("position:" + position);
            }
        });
    }

    @Override
    public int inflaterRootView() {
        return R.layout.fragment_recycler;
    }
}
