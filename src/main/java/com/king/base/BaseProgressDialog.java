package com.king.base;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author Jenly
 * @date 2015-4-20
 */
public class BaseProgressDialog extends Dialog{

//    private TextView tv;

    public BaseProgressDialog(Context context) {
        this(context,R.style.progress_dialog);
        initUI();
    }

    public BaseProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
        initUI();
    }

    protected BaseProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initUI();
    }


    private void initUI(){
        setContentView(new ProgressBar(getContext()));
        getWindow().getAttributes().gravity = Gravity.CENTER;
        setCanceledOnTouchOutside(false);
    }

}
