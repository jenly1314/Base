/*
 Copyright © 2015, 2016 Jenly Yu <a href="mailto:jenly1314@gmail.com">Jenly</a>

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

 */
package com.king.base.adapter;

import android.content.Context;

import com.king.base.adapter.holder.ViewHolder;

import java.util.List;

/**
 * 通用适配器
 *      在BaseAdapter基础之上将H:ViewHolder具体化，通过通用的ViewHolder根据控件的id得到对应控件，来进行相关的数据绑定操作
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 *
 */
public abstract class ViewHolderAdapter<T> extends BaseAdapter<T,ViewHolder> {


    public ViewHolderAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    public ViewHolderAdapter(Context context, List<T> listData, int layoutId) {
        super(context, listData, layoutId);
    }
}
