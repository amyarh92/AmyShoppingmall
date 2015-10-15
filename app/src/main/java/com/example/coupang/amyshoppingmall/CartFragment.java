package com.example.coupang.amyshoppingmall;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;


public class CartFragment extends Fragment {
    private TextView mHeadline;
    private String mCategory = "DEFULT CATEGORY";
    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cart,container,false);

        //Edit Headline
        mHeadline = (TextView)v.findViewById(R.id.headline);
        if (getArguments() != null){
            mCategory = getArguments().getString("category");
        }
        mHeadline.setText(mCategory);

        webView = (WebView)v.findViewById(R.id.webview_login);

        // 웹뷰에서 자바스크립트실행가능
        webView.getSettings().setJavaScriptEnabled(true);
        // 구글홈페이지 지정
        webView.loadUrl("http://192.168.212.224:8080/cart");
        // WebViewClient 지정
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        return v;
    }
}
