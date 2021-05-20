package com.power.fresh.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.power.common_opensurce.widget.ProgressView;
import com.power.fresh.R;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.helper.LogUtils;
import com.powerrich.common.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends UIActivity {


    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.progressview_progress)
    ProgressView mProgressView;

    public static void startActivity(Context context, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        Log.e("jsc", "WebViewActivity-Url:"+url);
        intent.putExtra("URL", url);
        context.startActivity(intent);
    }


    public static void startActivity(Context context, String url, String title) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("URL", url);
        intent.putExtra("TITLE", title);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initView() {
        String url = getIntent().getStringExtra("URL");
        String title = getIntent().getStringExtra("TITLE");
        setTitle(title);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        webView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (mProgressView==null) {
                    return;
                }
                if (newProgress==100) {
                    mProgressView.setVisibility(View.GONE);
                }else{

                }
                mProgressView.setProgress(newProgress);
            }
        });


        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setDefaultTextEncodingName("utf-8");//设置字符编码
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(false);
        webSetting.setBuiltInZoomControls(false);

        //设置加载进来的页面自适应手机屏幕
        webSetting.setUseWideViewPort(true);
        webSetting.setLoadWithOverviewMode(true);

        webSetting.setBlockNetworkImage(false);
        // 设置Application Caches缓存
        webSetting.setAppCacheEnabled(true);
        webSetting.setAppCacheMaxSize(50 * 1024 * 1024);
        // 开启database storage API 功能
        webSetting.setDatabaseEnabled(true);
        // 开启DOM storage API 功能
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setPluginState(WebSettings.PluginState.ON);
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);

        webView.loadUrl(url);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }
}
