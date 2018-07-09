package com.king.base.app;

import android.content.Intent;

import com.king.base.ContentActivity;
import com.king.base.app.fragment.ListFragment;
import com.king.base.app.fragment.RecyclerFragment;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public class FrameActivity extends ContentActivity {

    @Override
    protected void switchFragment(Intent intent) {
        int keyFragment = intent.getIntExtra(com.king.base.Constants.KEY_FRAGMENT,0);
        switch (keyFragment){
            case Constants.FRAGMENT_LIST:
                replaceFragment(ListFragment.newInstance());
                break;
            case Constants.FRAGMENT_RECYCLER_VIEW:
                replaceFragment(RecyclerFragment.newInstance());
                break;
            default:
                break;
        }
    }
}
