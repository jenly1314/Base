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

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/2/4
 */
public abstract class SplashActivity extends BaseActivity {

    public abstract @LayoutRes int getContentViewId();

    public abstract Animation.AnimationListener getAnimationListener();

    public View getRootView(){
        return getContentView(this);
    }

    protected Animation getAnimation(){
        return AnimationUtils.loadAnimation(getContext(), R.anim.splash_alpha);
    }

    protected void startAnimation(View rootView){
        Animation anim = getAnimation();
        anim.setAnimationListener(getAnimationListener());
        rootView.startAnimation(anim);
    }

    @Override
    public void initUI() {
        setContentView(getContentViewId());
    }


    @Override
    public void initData() {
        startAnimation(getRootView());
    }


}
