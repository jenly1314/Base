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
package com.king.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.king.base.util.StringUtils;
import com.king.base.util.SystemUtils;
import com.king.base.util.ToastUtils;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public abstract class BaseActivity extends AppCompatActivity implements  BaseInterface{

    private Dialog dialog;

    private BaseProgressDialog progressDialog;

    protected int curPage;

    protected boolean isStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        curPage = 1;
        initUI();
        initData();
        addListeners();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissDialog();
    }


    @Override
    protected void onResume() {
        super.onResume();
        isStop = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isStop = true;
        dismissProgressDialog();
    }

    @Override
    public void addListeners() {

    }

    public static View getContentView(Activity activity){
        ViewGroup view = (ViewGroup)activity.getWindow().getDecorView();
        FrameLayout content = view.findViewById(android.R.id.content);
        return content.getChildAt(0);
    }


    protected View inflate(@LayoutRes int id){
        return inflate(id,null);
    }

    protected View inflate(@LayoutRes int id, @Nullable ViewGroup root){
       return LayoutInflater.from(getContext()).inflate(id,root);
    }

    /**
     * use {@link #findViewById(int)}
     * @param id
     * @param <T>
     * @return
     */
    @Deprecated
    public <T extends View> T findView(@IdRes int id){
        return (T)findViewById(id);
    }

    protected void setOnClickListener(@IdRes int id,View.OnClickListener onClicklistener){
        findViewById(id).setOnClickListener(onClicklistener);
    }

    //-----------------------------------

    protected Context getContext(){
        return this;
    }

    protected Intent getIntent(Class<?> cls){
        return new Intent(getContext(),cls);
    }

    protected Intent getIntent(Class<?> cls,int flags){
        Intent intent = getIntent(cls);
        intent.setFlags(flags);
        return intent;
    }

    protected void startActivity(Class<?> cls){
        startActivity(getIntent(cls));
    }

    protected void startActivity(Class<?> cls,int flags){
        startActivity(getIntent(cls,flags));
    }

    protected void startActivityFinish(Class<?> cls){
        startActivity(cls);
        finish();
    }

    protected void startActivityFinish(Class<?> cls,int flags){
        startActivity(cls,flags);
        finish();
    }

    //-----------------------------------

    public void replaceFragment(@IdRes int resId,Fragment fragment){
        replaceFragment(resId,fragment,false);
    }

    public void replaceFragment(@IdRes int resId, Fragment fragment, boolean isBackStack) {
        FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(resId, fragment);
        if(isBackStack){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    //-----------------------------------

    protected void showToast(@StringRes  int resId){
        if(resId != Constants.NONE)
            ToastUtils.showToast(getContext(),resId);
    }

    protected void showLongToast(@StringRes  int resId){

        ToastUtils.showToast(getContext(),resId, Toast.LENGTH_LONG);
    }

    protected void showToast(CharSequence text){
        ToastUtils.showToast(getContext(),text);
    }

    protected void showLongToast(CharSequence text){
        ToastUtils.showToast(getContext(),text, Toast.LENGTH_LONG);
    }

    //-----------------------------------

    public boolean checkInput(TextView tv){
        return checkInput(tv,Constants.NONE);
    }

    public boolean checkInput(TextView tv,@StringRes int resId){
        return checkInput(tv,resId,false);
    }

    public boolean checkInput(TextView tv,@StringRes int resId,boolean isShake){

        if(StringUtils.isBlank(tv.getText())){
            if(isShake)
                startShake(tv,resId);
            else
                showToast(resId);
            return false;
        }

        return true;
    }

    public void startShake(View v,@StringRes int resId){
        startShake(v);
        showToast(resId);
    }

    public void startShake(View view){
        view.startAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.shake));
    }

    /**
     * 隐藏软键盘
     *
     * @param v
     */
    public void hideInputMethod(EditText v) {
        SystemUtils.hideInputMethod(getContext(),v);
    }

    /**
     * 显示软键盘
     *
     * @param v
     */
    public void showInputMethod(EditText v) {
        SystemUtils.showInputMethod(getContext(),v);
    }


    //-----------------------------------

    public Dialog getProgressDialog() {
        return progressDialog;
    }

    public Dialog getDialog() {
        return dialog;
    }

    protected void dismissProgressDialog(){
        dismissDialog(progressDialog);
    }

    protected void dismissDialog(){
        dismissDialog(dialog);
    }

    protected void dismissDialog(Dialog dialog){
        if(dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }

    protected void dismissDialogFragment(DialogFragment dialogFragment){
        if(dialogFragment != null){
            dialogFragment.dismiss();
        }
    }

    protected void showProgressDialog(){
        showProgressDialog(false);
    }

    protected void showProgressDialog(boolean isCancel){
        showProgressDialog(R.layout.progress_dialog,isCancel);
    }

    protected void showProgressDialog(@LayoutRes int resId){
        showProgressDialog(resId,false);
    }

    protected void showProgressDialog(@LayoutRes int resId,boolean isCancel){
        showProgressDialog(inflate(resId),isCancel);
    }

    protected void showProgressDialog(View v){
        showProgressDialog(v,false);
    }

    protected void showProgressDialog(View v,boolean isCancel){
        dismissProgressDialog();
        progressDialog =  BaseProgressDialog.newInstance(getContext());
        progressDialog.setContentView(v);
        progressDialog.setCanceledOnTouchOutside(isCancel);
        progressDialog.show();
    }

    protected void showDialog(View contentView){
        showDialog(contentView,Constants.DEFAULT_WIDTH_RATIO);
    }

    protected void showDialog(View contentView,boolean isCancel){
        showDialog(getContext(),contentView,R.style.dialog,Constants.DEFAULT_WIDTH_RATIO,isCancel);
    }

    protected void showDialog(View contentView,float widthRatio){
        showDialog(getContext(),contentView,widthRatio);
    }

    protected void showDialog(View contentView,float widthRatio,boolean isCancel){
        showDialog(getContext(),contentView,R.style.dialog,widthRatio,isCancel);
    }

    protected void showDialog(Context context,View contentView,float widthRatio){
        showDialog(context,contentView, R.style.dialog,widthRatio);
    }

    protected void showDialog(Context context, View contentView, @StyleRes int resId, float widthRatio){
        showDialog(context,contentView,resId,widthRatio,true);
    }

    protected void showDialog(Context context, View contentView, @StyleRes int resId, float widthRatio,final boolean isCancel){
        dismissDialog();
        dialog = new Dialog(context,resId);
        dialog.setContentView(contentView);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_BACK && isCancel){
                    dismissDialog();
                }
                return true;

            }
        });
        setDialogWindow(dialog,widthRatio);
        dialog.show();

    }

    protected void setDialogWindow(Dialog dialog,float widthRatio){
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int)(getWidthPixels()*widthRatio);
        window.setAttributes(lp);
    }


    protected void asyncThread(Runnable runnable){
        new Thread(runnable).start();
    }

    //-----------------------------------

    protected DisplayMetrics getDisplayMetrics(){
        return getResources().getDisplayMetrics();
    }

    protected int getWidthPixels(){
        return getDisplayMetrics().widthPixels;
    }

    protected int getHeightPixels(){
        return getDisplayMetrics().heightPixels;
    }

    //-----------------------------------

}
