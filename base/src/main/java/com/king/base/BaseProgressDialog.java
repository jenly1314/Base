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
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class BaseProgressDialog extends Dialog {


    public static BaseProgressDialog newInstance(Context context) {
        return new BaseProgressDialog(context);
    }


    public BaseProgressDialog(Context context) {
        this(context, R.style.progress_dialog);
    }

    public BaseProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
        initUI();
    }

    public BaseProgressDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initUI();
    }

    @Override
    public void setContentView(@NonNull View view) {
        if(view == null){
            view = new ProgressBar(getContext());
        }
        super.setContentView(view);
    }

    private void initUI() {
        getWindow().getAttributes().gravity = Gravity.CENTER;
        setCanceledOnTouchOutside(false);
    }

}