package com.king.base.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.king.base.adapter.holder.ViewHolder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * ListView通用适配器
 *      在HolderRecyclerAdapter基础之上将H:ViewHolder实例化，通过ViewHolder子类根据控件的id得到对应控件，来进行相关的数据绑定操作
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class BaseAdapter<T,H extends ViewHolder> extends HolderAdapter<T,H> {

    private int layoutId;

    public BaseAdapter(Context context, @LayoutRes int layoutId){
        this(context,null,layoutId);
    }

    public BaseAdapter(Context context, List<T> listData, @LayoutRes int layoutId) {
        super(context, listData);
        this.layoutId = layoutId;
    }


    @Override
    public View buildConvertView(LayoutInflater layoutInflater, T item, int position, ViewGroup parent) {
        return layoutInflater.inflate(layoutId,parent,false);
    }

    @Override
    public H buildHolder(View convertView, T item, int position) {
        return createViewHolder(convertView);
    }

    /**
     * 创建ViewHolder
     * @param view view
     * @return new ViewHolder
     */
    @SuppressWarnings("unchecked")
    protected H createViewHolder(View view) {
        Class temp = getClass();
        Class z = null;
        while (z == null && null != temp) {
            z = getInstancedGenericHClass(temp);
            temp = temp.getSuperclass();
        }
        H holder;
        // 泛型擦除会导致z为null
        if (z == null) {
            holder = (H) new ViewHolder(view);
        } else {
            holder = createGenericHInstance(z, view);
        }
        return holder != null ? holder : (H) new ViewHolder(view);
    }

    /**
     * 创建泛型{@link H}实例
     * @param z
     * @param view
     * @return
     */
    @SuppressWarnings("unchecked")
    private H createGenericHInstance(Class z, View view) {
        try {
            Constructor constructor;
            // 成员类和非静态类
            if (z.isMemberClass() && !Modifier.isStatic(z.getModifiers())) {
                constructor = z.getDeclaredConstructor(getClass(), View.class);
                constructor.setAccessible(true);
                return (H) constructor.newInstance(this, view);
            } else {
                constructor = z.getDeclaredConstructor(View.class);
                constructor.setAccessible(true);
                return (H) constructor.newInstance(view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取泛型{@link H}
     *
     * @param z
     * @return
     */
    private Class getInstancedGenericHClass(Class z) {
        Type type = z.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();
            for (Type temp : types) {
                if (temp instanceof Class) {
                    Class tempClass = (Class) temp;
                    if (ViewHolder.class.isAssignableFrom(tempClass)) {
                        return tempClass;
                    }
                } else if (temp instanceof ParameterizedType) {
                    Type rawType = ((ParameterizedType) temp).getRawType();
                    if (rawType instanceof Class && ViewHolder.class.isAssignableFrom((Class<?>) rawType)) {
                        return (Class<?>) rawType;
                    }
                }
            }
        }
        return null;
    }

}
