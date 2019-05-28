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
package com.king.base.fragment;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.king.base.BaseFragment;
import com.king.base.Constants;
import com.king.base.R;
import com.king.base.util.LogUtils;
import com.king.base.util.SystemUtils;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public  class WebFragment extends BaseFragment{

    public static final int OPR_WEBVIEW_BACK = 0x01;

    protected ProgressBar progressBar;

    protected LinearLayout vError;

    protected WebView webView;

    protected String url;

    protected boolean isError;

    private boolean isShowError;

    public static WebFragment newInstance() {

        Bundle args = new Bundle();

        WebFragment fragment = new WebFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int inflaterRootView() {
        return R.layout.fragment_web;
    }


    @Override
    public void initUI() {
        rootView.setFocusable(true);
        rootView.setFocusableInTouchMode(true);

        progressBar = findView(R.id.progressBar);
        progressBar.setMax(100);
        webView = findView(R.id.webView);
        vError = findView(R.id.vError);

        isShowError = addErrorView(vError);
    }

    @Override
    public void addListeners() {


    }

    @Override
    public void initData() {
        WebSettings ws = webView.getSettings();
        //是否允许脚本支持
        //是否允许脚本支持
        ws.setJavaScriptEnabled(true);
        ws.setDomStorageEnabled(true);

        ws.setJavaScriptCanOpenWindowsAutomatically(true);

        String str = getIntent().getStringExtra(Constants.KEY_URL);
        if(!TextUtils.isEmpty(str)){
            this.url = str;
        }

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                updateProgressBar(newProgress);
            }
        });
        webView.setWebViewClient(getWebViewClient());

        load(webView,url);
    }

    public WebView getWebView(){
        return webView;
    }

    public WebViewClient getWebViewClient(){
        return new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                LogUtils.d("startUrl:" + url);
                webView.setVisibility(View.VISIBLE);
                isError = false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtils.d("url:" + url);

                return super.shouldOverrideUrlLoading(view,url);

            }


            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                updateProgressBar(100);
            }


            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                LogUtils.w("errorCode:" + errorCode + "|failingUrl:" + failingUrl);
                showReceiveError();
            }


            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.cancel();
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideReceiveError();
            }
        };
    }

    /**
     *
     * @param group
     * @return  true表示已添加ErrorView并显示ErrorView/false表示不处理
     */
    public  boolean addErrorView(ViewGroup group){
        return false;
    };

    private void showReceiveError(){
        isError = true;
        if(SystemUtils.isNetWorkActive(getContext())){
            LogUtils.w("Page loading failed.");
        }else{
            LogUtils.w("Network unavailable.");
        }

        if(isShowError){
            vError.setVisibility(View.VISIBLE);
            webView.setVisibility(View.GONE);
        }


    }

    private void hideReceiveError(){
        if(isError){
            showReceiveError();
        }else{
            webView.setVisibility(View.VISIBLE);
            vError.setVisibility(View.GONE);
        }

    }

    /**
     * 加载url
     * @param webView
     * @param url
     */
    private void load(WebView webView,String url){
        LogUtils.d("url:" + url);
        if(!TextUtils.isEmpty(url)){
            webView.loadUrl(url);
        }

    }

    private boolean isGoBack(){
        return webView!=null && webView.canGoBack();
    }


    private void updateProgressBar(int progress){
        updateProgressBar(true,progress);
    }


    private void updateProgressBar(boolean isVisibility,int progress){
        if(progressBar != null){
            progressBar.setVisibility((isVisibility && progress<100) ? View.VISIBLE : View.GONE);
            progressBar.setProgress(progress);
        }

    }

}

