package com.king.base.app;

import android.content.Intent;
import android.view.View;

import com.king.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    public void initUI() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData() {

    }

    @Override
    public void addListeners() {

    }

    private void startFrameActivity(int keyFragment){
        Intent intent = new Intent(this,FrameActivity.class);
        intent.putExtra(com.king.base.Constants.KEY_FRAGMENT,keyFragment);
        startActivity(intent);
    }

    public void OnClick(View v){
        switch (v.getId()){
            case R.id.btnListView:
                startFrameActivity(Constants.FRAGMENT_LIST);
                break;
            case R.id.btnRecyclerView:
                startFrameActivity(Constants.FRAGMENT_RECYCLER_VIEW);
                break;
        }
    }
}
