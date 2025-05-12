# Base

![Image](app/src/main/ic_launcher-web.png)

[![JitPack](https://img.shields.io/jitpack/v/github/jenly1314/Base?logo=jitpack)](https://jitpack.io/#jenly1314/Base)
[![Download](https://img.shields.io/badge/download-APK-brightgreen?logo=github)](https://raw.githubusercontent.com/jenly1314/Base/master/app/release/app-release.apk)
[![API](https://img.shields.io/badge/API-15%2B-brightgreen?logo=android)](https://developer.android.com/guide/topics/manifest/uses-sdk-element#ApiLevels)
[![License](https://img.shields.io/github/license/jenly1314/Base?logo=open-source-initiative)](https://opensource.org/licenses/apache-2-0)



Base是针对于Android开发封装好一些常用的基类，主要包括通用的Adapter、Activity、Fragment、Dialog等、和一些常用的Util类，只为更简单。

> Base 3.x 在[Base 2.x](https://github.com/jenly1314/Base/tree/2.x) 的基础上进行了重构，最大的变化是将base-adapter和base-util提取了出来。

> 单独提取library主要是为了模块化，使其更加独立。在使用时需要用哪个库就引入库，这样就能尽可能的减少引入库的体积。

* base 主要是封装了常用的Activity、Fragment、DialogFragment、Dialog等作为基类，方便使用。
* base-adapter 主要是封装了各种Adapter、简化自定义Adapter步骤，让写自定义适配器从此更简单。
* base-util 主要是封装了一些常用的工具类。

## 效果展示

![Image](GIF.gif)

> 你也可以直接下载 [演示App](https://raw.githubusercontent.com/jenly1314/Base/master/app/app-release.apk) 体验效果

## 引入

### Gradle:

1. 在Project的 **build.gradle** 或 **setting.gradle** 中添加远程仓库

    ```gradle
    repositories {
        //...
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
    ```

2. 在Module的 **build.gradle** 中添加依赖项
   
   ```gradle
    //--------- AndroidX
    // base
    implementation 'com.github.jenly1314.Base:base:3.2.1-androidx'
    // adapter
    implementation 'com.github.jenly1314.Base:adapter:3.2.1-androidx'
    // util
    implementation 'com.github.jenly1314.Base:uitl:3.2.1-androidx'
    ```
    或
   
    ```gradle
    //--------- Android Support
    // base
    implementation 'com.github.jenly1314.Base:base:3.2.1'
    // adapter
    implementation 'com.github.jenly1314.Base:adapter:3.2.1'
    // util
    implementation 'com.github.jenly1314.Base:uitl:3.2.1'
    ```

## 使用

### 简要说明

Base主要实用地方体现在：出统一的代码风格，实用的各种基类，BaseActivity和BaseFragment里面还有许多实用的代码封装，只要用了Base，使用Fragment就感觉跟使用Activtiy基本是一样的。

### 代码示例

#### 通用的Adapter
```Java
/**
  *
  * 只需继承通用的适配器（ViewHolderAdapter或ViewHolderRecyclerAdapter），简单的几句代码，妈妈再也不同担心我写自定义适配器了。
  */
public class TestAdapter extends ViewHolderAdapter<String> {


    public TestAdapter(Context context, List<String> listData) {
        super(context, listData);
    }

    @Override
    public View buildConvertView(LayoutInflater layoutInflater,T t,int position, ViewGroup parent) {
        return inflate(R.layout.list_item,parent,false);
    }

    @Override
    public void bindViewDatas(ViewHolder holder, String s, int position) {
        holder.setText(R.id.tv,s);
    }
}

```

#### 基类BaseActivity
```Java
public class TestActivity extends BaseActivity {

    private TextView tv;
    private Button btn;

    @Override
    public void initUI() {
        //TODO:初始化UI
        setContentView(R.layout.activity_test);
        tv = findView(R.id.tv);
        btn = findView(R.id.btn);
    }

    @Override
    public void initData() {
        //TODO:初始化数据（绑定数据）
        tv.setText("text");
    }

}
```
#### GestureActivity
```Java
public class TestGestureActivity extends GestureActivity {

    private TextView tv;
    private Button btn;

    @Override
    public void initUI() {
        //TODO:初始化UI
        setContentView(R.layout.activity_test);
        tv = findView(R.id.tv);
        btn = findView(R.id.btn);
    }

    @Override
    public void initData() {
        //TODO:初始化数据（绑定数据）
        tv.setText("text");
    }

    @Override
    public void onLeftFling() {
        //TODO:向左滑动
    }

    @Override
    public boolean onRightFling() {
        //TODO:向右滑动，默认执行finish，返回为true表示拦截事件。
        return false;
    }
}
```
#### SplashActivity
```Java
public class TestSplashActivity extends SplashActivity {
    @Override
    public int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    public Animation.AnimationListener getAnimationListener() {
        return new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //TODO: 启动动画结束，可执行跳转逻辑
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }
}
```

#### BaseFragment
```Java
public class TestFragment extends BaseFragment {
    @Override
    public int inflaterRootView() {
        return R.layout.fragment_test;
    }

    @Override
    public void initUI() {
        //TODO:初始化UI
    }

    @Override
    public void initData() {
         //TODO:初始化数据（绑定数据）
    }

}
```
#### BaseDialogFragment
```Java
public class TestDialogFragment extends BaseDialogFragment {
    @Override
    public int inflaterRootView() {
        return R.layout.fragment_test_dialog;
    }

    @Override
    public void initUI() {
        //TODO:初始化UI
    }

    @Override
    public void initData() {
        //TODO:初始化数据（绑定数据）
    }


}
```

#### WebFragment
```Java
    WebFragment实现基本webView功能
```
#### 其他小功能

使用Log:
统一控制管理Log
```Java
 LogUtils.v();

 LogUtils.d();

 LogUtils.i();

 LogUtils.w();

 LogUtils.e();

 LogUtils.twf();

 LogUtils.println();
```

使用Toast
```Java
 showToast(CharSequence text);

 showToast(@StringRes  int resId);
```

使用Dialog
```Java
 showDialog(View v);
```
```Java
 showProgressDialog();

 showProgressDialog(@LayoutRes int resId);

 showProgressDialog(View v);
```
更多使用详情，请查看[app](app)中的源码使用示例或直接查看[API帮助文档](https://jitpack.io/com/github/jenly1314/Base/latest/javadoc/)

## 版本记录

#### v3.2.1：2019-7-1
*  优化部分细节，为迁移AndroidX版本做准备
*  支持AndroidX对应版本

#### v3.2.0：2019-5-28
*  统一版本，方便维护

#### v3.1.2: 很久以前…
*  未记录版本日志

## 相关推荐
- [MVVMFrame](https://github.com/jenly1314/MVVMFrame) 一个基于Google官方推出的JetPack构建的MVVM快速开发框架。
- [AppTemplate](https://github.com/jenly1314/AppTemplate) 一款基于 MVVMFrame 构建的App模板。
- [AppUpdater](http://github.com/jenly1314/AppUpdater) 一个专注于App更新，一键傻瓜式集成App版本升级的轻量开源库。
- [RetrofitHelper](http://github.com/jenly1314/RetrofitHelper) 一个支持动态改变BaseUrl，动态配置超时时长的Retrofit帮助类。
- [BaseUrlManager](http://github.com/jenly1314/BaseUrlManager) 一个BaseUrl管理器，主要用于打测试包时，一个App可动态切换到不同的开发环境或测试环境。
- [SuperTextView](http://github.com/jenly1314/SuperTextView) 一个在TextView的基础上扩展了几种动画效果的控件。
- [ImageViewer](http://github.com/AndroidKTX/ImageViewer) 一个图片查看器，一般用来查看图片详情或查看大图时使用。
- [GuidePage](http://github.com/AndroidKTX/GuidePage) 一个App欢迎引导页。一般用于首次打开App时场景，通过引导页指南，概述App特色等相关信息。
- [LogX](http://github.com/jenly1314/LogX) 一个轻量而强大的日志框架；好用不解释。
- [KVCache](http://github.com/jenly1314/KVCache) 一个便于统一管理的键值缓存库；支持无缝切换缓存实现。
- [AndroidKTX](http://github.com/AndroidKTX/AndroidKTX) 一个简化 Android 开发的 Kotlin 工具类集合。
- [AndroidUtil](http://github.com/AndroidUtil/AndroidUtil) 一个整理了Android常用工具类集合，平时在开发的过程中可能会经常用到。

---

![footer](https://jenly1314.github.io/page/footer.svg)
