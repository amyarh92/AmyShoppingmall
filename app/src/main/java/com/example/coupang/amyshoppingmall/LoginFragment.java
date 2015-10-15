package com.example.coupang.amyshoppingmall;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;


public class LoginFragment extends Fragment {
    private TextView mHeadline;
    private String mCategory = "DEFULT CATEGORY";
    private static WebView webView;

    private Handler mhandler;
    private boolean mFlag = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mhandler = new Handler(){
            @Override
        public void handleMessage(Message msg){
                if (msg.what == 0){
                    mFlag = false;
                }
            }
        };

    }

    public void myOnKeyDown(int keyCode) {
        if(webView.canGoBack()) {
            webView.goBack();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        //Edit Headline
        mHeadline = (TextView) v.findViewById(R.id.headline);
        if (getArguments() != null) {
            mCategory = getArguments().getString("category");
        }
        mHeadline.setText(mCategory);


        //webView created
        webView = (WebView) v.findViewById(R.id.webview_login);

        // 웹뷰에서 자바스크립트실행가능
        webView.getSettings().setJavaScriptEnabled(true);
        // login page지정
//        webView.loadUrl("http://192.168.0.7:8080/login");
        webView.loadUrl("http://192.168.212.224:8080/login");
        webView.setWebViewClient(new WebViewClientClass());
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

//        webView.setOnClickListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                //This is the filter
//                if (event.getAction() != KeyEvent.ACTION_DOWN)
//                    return true;
//
//
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//                    if (webView.canGoBack()) {
//                        webView.goBack();
//                        Log.d("webviewTAG", "canGoBack");
//                    } else {
////                        Log.d("webviewTAG","canNotGoBack");
//                        getActivity().onBackPressed();
//                    }
//
//                    return true;
//                }
//
//                return false;
//            }
//        });

        return v;
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

    }

    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }


    public static boolean canGoBack(){
        return webView.canGoBack();
    }

    public static void goBack(){
        webView.goBack();
    }

//    public boolean onKeyDown(int keyCode, KeyEvent event){
//        //back key pressed
//        if (keyCode == KeyEvent.KEYCODE_BACK){
//            //이전 페이지 볼수 있다면 이전 페이지를 보여줌
//            if (webView.canGoBack()){
//                webView.goBack();
//                return false;
//            }
//            else{
//                if (!mFlag){
//                    Toast.makeText(getActivity(), "'뒤로' 버튼을 한번 더 누르시면 앱이 종료됩니다.", Toast.LENGTH_LONG).show();
//                    mFlag = true;
//                    mhandler.sendEmptyMessageDelayed(0, 2000); // 2초이내 터치시
//                    return false;
//                }else {
//                    webView.destroy();
//                }
//            }
//        }
//        return super.onKeyDown(keyCode, event);
//    }

}
//
//    private class AndroidBridge {
//        public void setMessage(final String arg) {
//            handler.post(new Runnable() {
//                public void run() {
////                    mTextView.setText("받은 메시지 : \n" + arg);
//                }
//            });
//        }
//    }
//}
//
//class WebViewClientClass extends WebViewClient {
//    @Override
//    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//        if (url.equals("YOURLINK")) {
//            Intent intent = new Intent(getContext(), YourActivity.class);
//            startActivity(intent);
//            return false;
//        }else{
//            view.loadURL(url);
//            return true;
//        }
//    }
//
//}

