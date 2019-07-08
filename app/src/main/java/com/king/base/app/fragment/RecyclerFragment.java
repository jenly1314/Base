package com.king.base.app.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import com.king.base.BaseFragment;
import com.king.base.adapter.HolderRecyclerAdapter;
import com.king.base.adapter.divider.DividerItemDecoration;
import com.king.base.app.R;
import com.king.base.app.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public class RecyclerFragment extends BaseFragment {

    private SwipeRefreshLayout srl;

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
        srl = findViewById(R.id.srl);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        initListData();
        adapter = new RecyclerViewAdapter(getContext(),listData);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullRefresh();
            }
        });

        adapter.setOnItemClickListener(new HolderRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
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
            listData.add("RecyclerView Item" + i);
        }
    }

    private void refreshView(){
        adapter.notifyDataSetChanged();
        srl.setRefreshing(false);
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
                listData.add("RecyclerView Item" + listData.size());
                refreshView();
            }
        }.execute();
    }


    @Override
    public void addListeners() {

    }

    @Override
    public int inflaterRootView() {
        return R.layout.fragment_recycler;
    }
}
