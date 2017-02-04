package com.king.base.fragment;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
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
import com.king.base.R;
import com.king.base.model.EventMessage;
import com.king.base.util.LogUtils;
import com.king.base.util.SystemUtils;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
public class WebFragment extends BaseFragment{

    public static final int OPR_WEBVIEW_BACK = 0x01;

    protected ProgressBar progressBar;

    protected LinearLayout vError;

    protected WebView webView;

    protected String url;

    protected boolean isError;

    private boolean isShowError;

    public static WebFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString(KEY_URL,url);
        WebFragment fragment = new WebFragment();
        fragment.url = url;
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

        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK){

                    if(isGoBack()){
                        webView.goBack();
                    }else{
                        finish();
                    }
                    return true;
                }

                return false;
            }
        });
    }

    @Override
    public void initData() {
        WebSettings ws = webView.getSettings();
        //是否允许脚本支持
        ws.setJavaScriptEnabled(true);


        ws.setJavaScriptCanOpenWindowsAutomatically(true);

        ws.setSaveFormData(false);
//        ws.setAppCacheEnabled(false);
        ws.setCacheMode(WebSettings.LOAD_NO_CACHE);


        webView.setHorizontalScrollBarEnabled(false);

//        webView.addJavascriptInterface(new WebJavascriptInterface(),"android");

        String str = getIntent().getStringExtra(KEY_URL);
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


    public WebViewClient getWebViewClient(){
        return new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                LogUtils.d("startUrl:" + url);
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
    public boolean addErrorView(ViewGroup group){

        return false;
    }

    private void showReceiveError(){
        isError = true;
        if(SystemUtils.isNetWorkActive(context)){
            LogUtils.w("Page loading failed.");
        }else{
            LogUtils.w("Network unavailable.");
        }

        if(isShowError){
            vError.setVisibility(View.VISIBLE);
        }


    }

    private void hideReceiveError(){
        if(isError){
            showReceiveError();
        }else{
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

        progressBar.setVisibility((isVisibility && progress<100) ? View.VISIBLE : View.GONE);
        progressBar.setProgress(progress);
    }


    @Override
    public void onEventMessage(EventMessage em) {
        if(isStop){
            return;
        }

        switch (em.what){
            case OPR_WEBVIEW_BACK:
                if(isGoBack()){
                    webView.goBack();
                }else{
                    finish();
                }
                break;
        }
    }

}

