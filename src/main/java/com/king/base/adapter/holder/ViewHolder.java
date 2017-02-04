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
package com.king.base.adapter.holder;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.regex.Pattern;


/**
 * 通用的ViewHolder
 *      继承 RecyeclerView.ViewHolder 主要是为了通用性更强
 *
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 *
 */
public class ViewHolder extends RecyclerView.ViewHolder{

    private SparseArray<View> views;

    private View convertView;

    public ViewHolder (View convertView){
        super(convertView);
        this.convertView = convertView;
        views = new SparseArray<>();
    }

    public View getConvertView(){
        return convertView;
    }

    private <T extends View> T findView(@IdRes int id){

        return (T)convertView.findViewById(id);
    }

    public <T extends  View> T getView(@IdRes int id){

        View v = views.get(id);
        if(v == null){
            v = findView(id);
            views.put(id,v);
        }

        return (T)v;
    }

    //---------------------- 控件常用设置

    public View setBackgroundResource(@IdRes int id,int resId){
        View v = getView(id);
        v.setBackgroundResource(resId);
        return v;
    }

    @TargetApi(16)
    public View setBackground(@IdRes int id,Drawable drawable){
        View v = getView(id);
        v.setBackground(drawable);
        return v;
    }

    public View setBackgroundColor(@IdRes int id,int color){
        View v = getView(id);
        v.setBackgroundColor(color);
        return v;
    }

    public View setTag(@IdRes int id,Object tag){
        View v = getView(id);
        v.setTag(tag);
        return v;
    }

    public View setTag(@IdRes int id,int key,Object tag){
        View v = getView(id);
        v.setTag(key,tag);
        return v;
    }

    public View setVisibility(@IdRes int id,int visibility){
        View v = getView(id);
        v.setVisibility(visibility);
        return v;
    }

    public View setVisibility(@IdRes int id,boolean isVisible){
        View v = getView(id);
        if(isVisible)
            v.setVisibility(View.VISIBLE);
        v.setVisibility(View.GONE);
        return v;
    }

    public void setAlpha(@IdRes int id,float alpha){
        View v = getView(id);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            v.setAlpha(alpha);
        }else{
            Animation anim = new AlphaAnimation(alpha,alpha);
            anim.setFillAfter(true);
            v.startAnimation(anim);
        }

    }

    public TextView setCompoundDrawableLeft(@IdRes int id,Drawable drawable){
        return setCompoundDrawables(id,drawable,null,null,null);
    }

    public TextView setCompoundDrawableTop(@IdRes int id,Drawable drawable){
        return setCompoundDrawables(id,null,drawable,null,null);
    }

    public TextView setCompoundDrawableRight(@IdRes int id,Drawable drawable){
        return setCompoundDrawables(id,null,null,drawable,null);
    }

    public TextView setCompoundDrawableBottom(@IdRes int id,Drawable drawable){
        return setCompoundDrawables(id,null,null,null,drawable);
    }


    public TextView setCompoundDrawables(@IdRes int id,Drawable left,Drawable top,Drawable right,Drawable bottom){
        TextView tv = getView(id);
        tv.setCompoundDrawables(left, top, right, bottom);
        return tv;
    }

    public TextView setCompoundDrawablePadding(@IdRes int id,int padding){
        TextView tv = getView(id);
        tv.setCompoundDrawablePadding(padding);
        return tv;
    }

    public TextView setCompoundDrawablesWithIntrinsicBounds(@IdRes int id,Drawable left,Drawable top,Drawable right,Drawable bottom){
        TextView tv = getView(id);
        tv.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        return tv;
    }

    public TextView setCompoundDrawablesWithIntrinsicBounds(@IdRes int id,int left,int top,int right,int bottom){
        TextView tv = getView(id);
        tv.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        return tv;
    }

    public TextView setText(@IdRes int id,@StringRes int resId){
        TextView tv = getView(id);
        tv.setText(resId);
        return tv;
    }

    public TextView setText(@IdRes int id,CharSequence text){
        TextView tv = getView(id);
        tv.setText(text);
        return tv;
    }

    public TextView setTextColor(@IdRes int id,int color){
        TextView tv = getView(id);
        tv.setTextColor(color);
        return tv;
    }

    public TextView setTextColor(@IdRes int id,ColorStateList colors){
        TextView tv = getView(id);
        tv.setTextColor(colors);
        return tv;
    }

    public TextView setTypeface(@IdRes int id,Typeface tf){
        TextView tv = getView(id);
        tv.setTypeface(tf);
        return tv;
    }

    public TextView setTypeface(@IdRes int id,Typeface tf, int style){
        TextView tv = getView(id);
        tv.setTypeface(tf,style);
        return tv;
    }

    public TextView addLinks(@IdRes int id){
        TextView tv = getView(id);
        Linkify.addLinks(tv,Linkify.ALL);
        return tv;
    }

    public TextView addLinks(@IdRes int id,int mask){
        TextView tv = getView(id);
        Linkify.addLinks(tv,mask);
        return tv;
    }
    public TextView addLinks(@IdRes int id, Pattern pattern, String scheme){
        TextView tv = getView(id);
        Linkify.addLinks(tv,pattern,scheme);
        return tv;
    }


    public ImageView setImageResource(@IdRes int id,int resId){
        ImageView iv = getView(id);
        iv.setImageResource(resId);
        return iv;
    }

    public ImageView setImageBitmap(@IdRes int id,Bitmap bitmap){
        ImageView iv = getView(id);
        iv.setImageBitmap(bitmap);
        return iv;
    }

    public ImageView setImageDrawable(@IdRes int id,Drawable drawable){
        ImageView iv = getView(id);
        iv.setImageDrawable(drawable);
        return iv;
    }


    public Checkable setChecked(@IdRes int id,boolean isChecked){
        Checkable checkable = getView(id);
        checkable.setChecked(isChecked);
        return checkable;
    }

    public boolean isChecked(@IdRes int id){
        Checkable checkable = getView(id);
        return checkable.isChecked();
    }

    public Checkable toggle(@IdRes int id){
        Checkable checkable = getView(id);
        checkable.toggle();
        return checkable;
    }

    public ProgressBar setProgress(@IdRes int id,int progress){
        ProgressBar progressBar = getView(id);
        progressBar.setProgress(progress);
        return progressBar;
    }

    public ProgressBar setMax(@IdRes int id,int max){
        ProgressBar progressBar = getView(id);
        progressBar.setMax(max);
        return progressBar;
    }

    public RatingBar setRating(@IdRes int id,float rating){
        RatingBar  ratingBar = getView(id);
        ratingBar.setRating(rating);
        return ratingBar;
    }

    public RatingBar setRating(@IdRes int id,float rating,int max){
        RatingBar  ratingBar = getView(id);
        ratingBar.setRating(rating);
        ratingBar.setMax(max);
        return ratingBar;
    }

    public RatingBar setNumStars(@IdRes int id,int numStars){
        RatingBar  ratingBar = getView(id);
        ratingBar.setNumStars(numStars);
        return ratingBar;
    }

    //---------------------- 监听事件

    public void setOnClickListener(@IdRes int id, View.OnClickListener onClickListener){
        getView(id).setOnClickListener(onClickListener);
    }

    public void setOnTouchListener(@IdRes int id, View.OnTouchListener onTouchListener){
        getView(id).setOnTouchListener(onTouchListener);
    }

    public void setOnLongClickListener(@IdRes int id, View.OnLongClickListener onLongClickListener){
        getView(id).setOnLongClickListener(onLongClickListener);
    }

    public void setOnKeyListener(@IdRes int id, View.OnKeyListener onKeyListener){
        getView(id).setOnKeyListener(onKeyListener);
    }

}
