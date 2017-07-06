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
package com.king.base.util;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Jenly
 */
public class ToastUtils {

    private static Toast toast;

    private ToastUtils(){
        throw new AssertionError();
    }

    public static void showToast(Context context,int resId){
        showToast(context,context.getResources().getString(resId));
    }

    public static void showToast(Context context,int resId,int duration){
        showToast(context,context.getResources().getString(resId),duration);
    }

    public static void showToast(Context context,CharSequence text){
        showToast(context,text,Toast.LENGTH_SHORT);
    }

    public static void showToast(Context context,String text,int duration,Object...args){
        showToast(context,String.format(text,args),duration);
    }

    public static void showToast(Context context,CharSequence text,int duration){

        if(toast == null){
            toast =  Toast.makeText(context,text,duration);
        }else{
            toast.setText(text);
            toast.setDuration(duration);
        }
        toast.show();
    }
}
