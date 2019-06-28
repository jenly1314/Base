package com.king.base.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.king.base.adapter.ViewHolderAdapter;
import com.king.base.adapter.holder.ViewHolder;
import com.king.base.app.R;

import java.util.List;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */

public class ListAdapter extends ViewHolderAdapter<String> {

    public ListAdapter(Context context, List<String> listData) {
        super(context, listData,R.layout.list_item);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, String s, int position) {
        holder.setText(R.id.tv,s);
    }
}
