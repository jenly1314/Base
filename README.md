# Base

![Image](app/src/main/ic_launcher-web.png)

[![Download](https://img.shields.io/badge/download-App-blue.svg)](https://raw.githubusercontent.com/jenly1314/Base/master/app/release/app-release.apk)
[![JitPack](https://jitpack.io/v/jenly1314/Base.svg)](https://jitpack.io/#jenly1314/Base)
[![CI](https://travis-ci.org/jenly1314/Base.svg?branch=master)](https://travis-ci.org/jenly1314/Base)
[![CircleCI](https://circleci.com/gh/jenly1314/Base.svg?style=svg)](https://circleci.com/gh/jenly1314/Base)
[![API](https://img.shields.io/badge/API-15%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=15)
[![License](https://img.shields.io/badge/license-Apche%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Blog](https://img.shields.io/badge/blog-Jenly-9932CC.svg)](https://jenly1314.github.io/)

Base是针对于Android开发封装好一些常用的基类，主要包括通用的Adapter、Activity、Fragment、Dialog等、和一些常用的Util类，只为更简单。

> Base 3.x 在[Base 2.x](https://github.com/jenly1314/Base/tree/2.x) 的基础上进行了重构，最大的变化是将base-adapter和base-util提取了出来。

> 单独提取library主要是为了模块化，使其更加独立。在使用时需要用哪个库就引入库，这样就能尽可能的减少引入库的体积。

* base 主要是封装了常用的Activity、Fragment、DialogFragment、Dialog等作为基类，方便使用。
* base-adapter 主要是封装了各种Adapter、简化自定义Adapter步骤，让写自定义适配器从此更简单。
* base-util 主要是封装了一些常用的工具类。

### [AndroidX version](https://github.com/jenly1314/Base/tree/androidx)

## Gif展示(示例App)

![Image](GIF.gif)

## 引入

### Maven：
```maven
//base
<dependency>
  <groupId>com.king.base</groupId>
  <artifactId>base</artifactId>
  <version>3.2.1</version>
  <type>pom</type>
</dependency>

//base-adapter
<dependency>
  <groupId>com.king.base</groupId>
  <artifactId>adapter</artifactId>
  <version>3.2.1</version>
  <type>pom</type>
</dependency>

//base-util
<dependency>
  <groupId>com.king.base</groupId>
  <artifactId>util</artifactId>
  <version>3.2.1</version>
  <type>pom</type>
</dependency>
```
### Gradle:
```gradle
//---------- AndroidX 版本
//base
implementation 'com.king.base:base:3.2.1-androidx'

//base-adapter
implementation 'com.king.base:adapter:3.2.1-androidx'

//base-util
implementation 'com.king.base:util:3.2.1-androidx'


//---------- Android 版本
//base
implementation 'com.king.base:base:3.2.1'

//base-adapter
implementation 'com.king.base:adapter:3.2.1'

//base-util
implementation 'com.king.base:util:3.2.1'


```
### Lvy:
```lvy
//base
<dependency org='com.king.base' name='base' rev='3.2.1'>
  <artifact name='$AID' ext='pom'></artifact>
</dependency>

//base-adapter
<dependency org='com.king.base' name='adapter' rev='3.2.1'>
  <artifact name='$AID' ext='pom'></artifact>
</dependency>

//base-util
<dependency org='com.king.base' name='util' rev='3.2.1'>
  <artifact name='$AID' ext='pom'></artifact>
</dependency>
```

###### 如果Gradle出现compile失败的情况，可以在Project的build.gradle里面添加如下：（也可以使用上面的GitPack来complie）
```gradle
allprojects {
    repositories {
        //...
        maven { url 'https://dl.bintray.com/jenly/maven' }
    }
}
```

### 引入的库：
```gradle
//---------- AndroidX 版本
//base
compileOnly 'androidx.appcompat:appcompat:1.0.0+'
compileOnly 'com.king.base:util:3.2.1-androidx'

//base-adapter
compileOnly 'androidx.appcompat:appcompat:1.0.0+'
compileOnly 'androidx.recyclerview:recyclerview:1.0.0+'

//base-util
compileOnly 'androidx.appcompat:appcompat:1.0.0+'

```

```gradle
//---------- Android 版本
//base
compileOnly 'com.android.support:appcompat-v7:28.0.0'
compileOnly 'com.king.base:util:3.2.1'

//base-adapter
compileOnly 'com.android.support:appcompat-v7:28.0.0'
compileOnly 'com.android.support:recyclerview-v7:28.0.0'

//base-util
compileOnly 'com.android.support:appcompat-v7:28.0.0'

```


## 简要说明：
Base主要实用地方体现在：出统一的代码风格，实用的各种基类，BaseActivity和BaseFragment里面还有许多实用的代码封装，只要用了Base，使用Fragment就感觉跟使用Activtiy基本是一样的。


## 代码示例：

### 通用的Adapter
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

### 基类BaseActivity
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
### GestureActivity
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
### SplashActivity
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

### BaseFragment
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
### BaseDialogFragment
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

### WebFragment
```Java
    WebFragment实现基本webView功能
```
### 其他小功能

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

[App](app)中的源码使用示例或直接查看[API帮助文档](https://jenly1314.github.io/projects/Base/doc/)。更多实用黑科技，请速速使用Base体会吧。

## 版本记录

#### v3.2.1：2019-7-1
*  优化部分细节，为迁移AndroidX版本做准备
*  支持AndroidX对应版本

#### v3.2.0：2019-5-28
*  统一版本，方便维护

#### v3.1.2: 很久以前…
*  未记录版本日志

## 赞赏
如果您喜欢Base，或感觉Base帮助到了您，可以点右上角“Star”支持一下，您的支持就是我的动力，谢谢 :smiley:<p>
您也可以扫描下面的二维码，请作者喝杯咖啡 :coffee:
    <div>
        <img src="https://jenly1314.github.io/image/pay/wxpay.png" width="280" heght="350">
        <img src="https://jenly1314.github.io/image/pay/alipay.png" width="280" heght="350">
        <img src="https://jenly1314.github.io/image/pay/qqpay.png" width="280" heght="350">
        <img src="https://jenly1314.github.io/image/alipay_red_envelopes.jpg" width="233" heght="350">
    </div>

## 关于我
   Name: <a title="关于作者" href="https://about.me/jenly1314" target="_blank">Jenly</a>

   Email: <a title="欢迎邮件与我交流" href="mailto:jenly1314@gmail.com" target="_blank">jenly1314#gmail.com</a> / <a title="给我发邮件" href="mailto:jenly1314@vip.qq.com" target="_blank">jenly1314#vip.qq.com</a>

   CSDN: <a title="CSDN博客" href="http://blog.csdn.net/jenly121" target="_blank">jenly121</a>

   Github: <a title="Github开源项目" href="https://github.com/jenly1314" target="_blank">jenly1314</a>

   加入QQ群: <a title="点击加入QQ群" href="http://shang.qq.com/wpa/qunwpa?idkey=8fcc6a2f88552ea44b1411582c94fd124f7bb3ec227e2a400dbbfaad3dc2f5ad" target="_blank">20867961</a>
   <div>
       <img src="https://jenly1314.github.io/image/jenly666.png">
       <img src="https://jenly1314.github.io/image/qqgourp.png">
   </div>
