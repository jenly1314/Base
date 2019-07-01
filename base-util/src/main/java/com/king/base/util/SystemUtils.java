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

import android.Manifest;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.File;
import java.util.List;
import java.util.Locale;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class SystemUtils {



    private SystemUtils(){
        throw new AssertionError();
    }

    /**
     * 异步线程
     * @param runnable
     */
    public static void asyncThread(Runnable runnable){
        new Thread(runnable).start();
    }


    public static boolean isNetWorkActive(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null && activeNetInfo.isConnected();
    }

    /**
     * 发送按键按下事件
     * @param keyCode
     */
    public static void sendKeyCode(final int keyCode) {
        asyncThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(keyCode);
                } catch (Exception e) {
                    LogUtils.e("Exception when sendPointerSync", e);
                }
            }
        });
    }

    /**
     * 退格删除键
     *
     * @param et
     */
    public static void deleteClick(EditText et) {
        final KeyEvent keyEventDown = new KeyEvent(KeyEvent.ACTION_DOWN,
                KeyEvent.KEYCODE_DEL);
        et.onKeyDown(KeyEvent.KEYCODE_DEL, keyEventDown);
    }


    /**
     * 调用打电话界面
     *
     * @param context
     * @param phoneNumber
     */
    public static void call(Context context, String phoneNumber) {

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(String.format("tel:%s", phoneNumber)));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
    }


    /**
     * 调用发短信界面
     *
     * @param context
     * @param phoneNumber
     */
    public static void sendSMS(Context context, String phoneNumber) {

        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(String.format("smsto:%s", phoneNumber)));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
    }

    /**
     * 发短信
     *
     * @param phoneNumber
     * @param msg
     *
     */
    public static void sendSMS(String phoneNumber, String msg) {

        SmsManager sm = SmsManager.getDefault();

        List<String> msgs = sm.divideMessage(msg);

        for (String text : msgs) {
            sm.sendTextMessage(phoneNumber, null, text, null, null);
        }

    }

    /**
     * 拍照
     *
     * @param activity
     * @param requestCode
     */
    public static void imageCapture(Activity activity, int requestCode) {
        imageCapture(activity, null, requestCode);
    }

    /**
     * 拍照
     *
     * @param activity
     * @param path
     * @param requestCode
     */
    public static void imageCapture(Activity activity, String path,
                                    int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (!TextUtils.isEmpty(path)) {
            Uri uri = null;
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                uri = FileProvider.getUriForFile(activity, activity.getPackageName() + ".fileProvider", new File(path));
            }else{
                uri = Uri.fromFile(new File(path));
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 拍照
     *
     * @param fragment
     * @param path
     * @param requestCode
     */
    public static void imageCapture(Fragment fragment, String path,
                                    int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (!TextUtils.isEmpty(path)) {
            Uri uri = null;
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                uri = FileProvider.getUriForFile(fragment.getContext(), fragment.getContext().getPackageName() + ".fileProvider", new File(path));
            }else{
                uri = Uri.fromFile(new File(path));
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        fragment.startActivityForResult(intent, requestCode);
    }

    /**
     * 获取包信息
     *
     * @param context
     * @return
     */
    public static PackageInfo getPackageInfo(Context context) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            LogUtils.e(e);
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return packageInfo;
    }

    /**
     * 通过APK路径获取包信息
     * @param context
     * @param archiveFilePath
     * @return
     */
    public static PackageInfo getPackageInfo(Context context, String archiveFilePath) {
        try{
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageArchiveInfo(archiveFilePath, PackageManager.GET_ACTIVITIES);
            return packageInfo;
        }catch (Exception e){
            LogUtils.e(e);
        }
        return null;
    }

    /**
     * 获取当前应用包的版本名称
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        return packageInfo != null ? packageInfo.versionName : null;
    }

    /**
     * 获取当前应用包的版本码
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        return packageInfo != null ? packageInfo.versionCode : 0;
    }

    /**
     * 跳转到app详情设置界面
     * @param context
     */
    public static void startAppDetailSetings(Context context){
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,uri);
        context.startActivity(intent);
    }

    /**
     * 安装apk
     * @param context
     * @param path
     */
    public static void installApk(Context context,String path){
        installApk(context,new File(path));
    }

    /**
     * 安装apk
     * @param context
     * @param file
     */
    public static void installApk(Context context,File file){
        installApk(context,file,context.getPackageName() + ".fileProvider");
    }

    /**
     * 安装apk
     * @param context
     * @param file
     * @param authority
     */
    public static void installApk(Context context,File file,String authority){

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        Uri uriData;
        String type = "application/vnd.android.package-archive";
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            uriData = FileProvider.getUriForFile(context, authority, file);
        }else{
            uriData = Uri.fromFile(file);
        }
        intent.setDataAndType(uriData, type);
        context.startActivity(intent);
    }


    /**
     * 卸载apk
     * @param context
     * @param packageName
     */
    public static void uninstallApk(Context context,String packageName){

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uriData = Uri.parse("package:" + packageName);
        intent.setData(uriData);
        context.startActivity(intent);
    }

    /**
     * 卸载
     * @param context
     */
    public static void uninstallApk(Context context){
        uninstallApk(context,context.getPackageName());
    }


    /**
     * 检测权限
     * @param context
     * @param permission
     * @return
     */
    public static boolean checkSelfPermission(Context context, @NonNull String permission){
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }


    /**
     * 申请权限
     * @param activity
     * @param permission
     * @param requestCode
     */
    public static void requestPermission(Activity activity, @NonNull String permission, int requestCode){
        ActivityCompat.requestPermissions(activity,new String[]{permission}, requestCode);
    }

    /**
     * 显示申请授权
     * @param activity
     * @param permission
     */
    public static void shouldShowRequestPermissionRationale(Activity activity, @NonNull String permission){
        ActivityCompat.shouldShowRequestPermissionRationale(activity,permission);
    }


    /**
     * 隐藏软键盘
     *
     * @param context
     * @param v
     */
    public static void hideInputMethod(Context context,EditText v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(),0);

    }

    /**
     * 显示软键盘
     *
     * @param context
     * @param v
     */
    public static void showInputMethod(Context context,EditText v) {
        v.requestFocus();
        InputMethodManager imm = (InputMethodManager)context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v,InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * APK是否存在
     * @param context
     * @param versionCode
     * @param file
     * @return
     */
    public static boolean apkExists(Context context,int versionCode,File file){
        if(file!=null && file.exists()){
            String packageName = context.getPackageName();
            PackageInfo packageInfo = getPackageInfo(context,file.getAbsolutePath());
            if(packageInfo!=null && versionCode == packageInfo.versionCode){//比对versionCode
                ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                if(applicationInfo!=null && packageName.equals(applicationInfo.packageName)){//比对packageName
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return 语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限)
     *
     * @return 手机IMEI
     */
    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Activity.TELEPHONY_SERVICE);
        if (tm != null) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    return tm.getImei();
                }
                return tm.getDeviceId();
            }
        }
        return "";
    }

}
