package com.example.coupang.amyshoppingmall;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.TextView;


public class PDPFragment extends Fragment {
    private TextView mHeadline;
    private WebView webView;
    private Long mProductId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pd,container,false);

        webView = (WebView)v.findViewById(R.id.webview_login);

        if (getArguments() != null){
            mProductId = getArguments().getLong("productId");
        }

        // 웹뷰에서 자바스크립트실행가능
        webView.getSettings().setJavaScriptEnabled(true);
        // pdp page url 지정
//        webView.loadUrl("http://192.168.0.7:8080/pdp?productId="+mProductId);
        webView.loadUrl("http://192.168.212.224:8080/pdp?productId="+mProductId);
        // WebViewClient 지정
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        return v;
    }

}
