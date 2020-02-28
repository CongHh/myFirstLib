package com.shencode.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static com.shencode.mylibrary.test.test;

public class MainActivity extends AppCompatActivity {
    private WebView web_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        web_view = findViewById(R.id.web_view);

        final String cURL = "http://60.191.23.20:8443/Model.html";
        web_view.loadUrl(cURL);

        WebSettings settings = web_view.getSettings();
        settings.setJavaScriptEnabled(true);// 这样网页就可加载JavaScript了
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setBuiltInZoomControls(true);// 显示放大缩小按钮
        settings.setSupportZoom(true);// 允许放大缩小
        settings.setSupportMultipleWindows(true);

        web_view.removeJavascriptInterface("searchBoxJavaBridge_");

        //阻止跳转浏览器
        web_view.setWebViewClient(new WebViewClient(){
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(String.valueOf(request.getUrl()));
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        test();
    }
}
