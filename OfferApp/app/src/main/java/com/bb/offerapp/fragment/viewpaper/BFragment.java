package com.bb.offerapp.fragment.viewpaper;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bb.offerapp.R;

/**
 * Created by bb on 2016/11/18.
 */
public class BFragment extends Fragment {


    private String url = "http://music.baidu.com/songs/hot/";
    private WebView webView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View  view = inflater.inflate(R.layout.recycle_b_fragment, container, false);
        webView = (WebView) view.findViewById(R.id.webView);

        init();
        return view;
    }

    private void init() {


        // WebView加载本地资源
        // webView.loadUrl("file:///android_asset/example.html");
        // WebView加载web资源
        webView.loadUrl(url);
        // 覆盖WebView默认通过第三方或者是系统浏览器打开网页的行为，使得网页可以在WebVIew中打开
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制网页在WebView中去打开，如果为false调用系统浏览器或第三方浏览器去打开
                view.loadUrl(url);
                return true;
            }
            //WebViewClient帮助WebView去处理一些页面控制和请求通知

        });
        //启用支持JavaScript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        //WebView加载页面优先使用缓存加载
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);



    }


}
