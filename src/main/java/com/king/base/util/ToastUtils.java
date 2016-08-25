package com.king.base.util;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Jenly
 * @date 2014-6-22
 */
public class ToastUtils {

    private static Toast toast;

    private ToastUtils(){
        throw new AssertionError();
    }

    public static void showToast(Context context,int resId){
        showToast(context,context.getResources().getString(resId));
    }

    public static void showToast(Context context,int resId,Object...args){
        showToast(context,String.format(context.getResources().getString(resId),args));
    }


    public static void showToast(Context context,int resId,int duration,Object...args){
        showToast(context,String.format(context.getResources().getString(resId),args),duration);
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
