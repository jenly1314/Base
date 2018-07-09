# Base
[![Download](https://img.shields.io/badge/download-App-blue.svg)](https://raw.githubusercontent.com/jenly1314/Base/master/app/app-release.apk)
[![](https://jitpack.io/v/jenly1314/Base.svg)](https://jitpack.io/#jenly1314/Base)
[![License](https://img.shields.io/badge/license-Apche%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Blog](https://img.shields.io/badge/blog-Jenly-9932CC.svg)](http://blog.csdn.net/jenly121)

Base是针对于Android开发封装好一些常用的基类，主要包括通用的Adapter、Activity、Fragment、Dialog等、和一些常用的Util类，只为更简单。

> Base 3.x 在[Base 2.x](https://github.com/jenly1314/Base/tree/2.x) 的基础上进行了重构，最大的变化是将base-adapter和base-util提取了出来。
> 单独提取library主要是为了模块化，使其更加独立。在使用时需要用哪个库就引入库，这样就能尽可能的减少引入库的体积。

* base 主要是封装了常用的Activity、Fragment、DialogFragment、Dialog等作为基类，方便使用。
* base-adapter 主要是封装了各种Adapter、简化步骤，适用于各种列表相关控件。妈妈再也不用担心写自定义适配器了。
* base-util 主要是封装了一些常用的工具类。



## Gif展示(示例App)

![Image](GIF.gif)

## 引入

### Maven：
```maven
//base
<dependency>
  <groupId>com.king.base</groupId>
  <artifactId>base</artifactId>
  <version>3.1.1</version>
  <type>pom</type>
</dependency>

//base-adapter
<dependency>
  <groupId>com.king.base</groupId>
  <artifactId>adapter</artifactId>
  <version>1.1.2</version>
  <type>pom</type>
</dependency>

//base-util
<dependency>
  <groupId>com.king.base</groupId>
  <artifactId>util</artifactId>
  <version>1.1.2</version>
  <type>pom</type>
</dependency>
```
### Gradle:
```gradle

//base
compile 'com.king.base:base:3.1.1'

//base-adapter
compile 'com.king.base:adapter:1.1.2'

//base-util
compile 'com.king.base:util:1.1.2'
```
### Lvy:
```lvy
//base
<dependency org='com.king.base' name='base' rev='3.1.1'>
  <artifact name='$AID' ext='pom'></artifact>
</dependency>

//base-adapter
<dependency org='com.king.base' name='adapter' rev='1.1.2'>
  <artifact name='$AID' ext='pom'></artifact>
</dependency>

//base-util
<dependency org='com.king.base' name='util' rev='1.1.2'>
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
//base
provided 'com.android.support:appcompat-v7:25.3.+'
provided 'com.king.base:util:1.1.2'
```

```gradle
//base-adapter
provided 'com.android.support:appcompat-v7:25.3.+'
provided 'com.android.support:recyclerview-v7:25.3.+'
```

```gradle
//base-util
provided 'com.android.support:appcompat-v7:25.3.+'
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

    @Override
    public void addListeners() {
        //TODO:添加监听事件
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
    public void addListeners() {
        //TODO:添加监听事件
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

    @Override
    public void addListeners() {
        //TODO:添加监听事件
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

    @Override
    public void addListeners() {
        //TODO:添加监听事件
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

[App](app)中有主要源码使用示例。更多实用黑科技，请速速使用Base体会吧。

## 关于我
   Name: Jenly

   Email: jenly1314@gmail.com / jenly1314@vip.qq.com

   CSDN: http://www.csdn.net/jenly121

   Github: https://github.com/jenly1314

   微信公众号:

   ![公众号](http://olambmg9j.bkt.clouddn.com/jenly666.jpg)

   加入QQ群: [20867961](http://shang.qq.com/wpa/qunwpa?idkey=8fcc6a2f88552ea44b1411582c94fd124f7bb3ec227e2a400dbbfaad3dc2f5ad)
   
## License

    Copyright © 2015, 2016 Jenly Yu 

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


