package com.example.coupang.amyshoppingmall;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by coupang on 2015. 8. 20..
 */
public abstract class ContentVolley {
    public ContentVolley(Context context) {
        this.requestQueue = Volley.newRequestQueue(context);
        this.context = context;
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }

    private RequestQueue requestQueue;
    private Context context;

    public abstract void requestData();
}
