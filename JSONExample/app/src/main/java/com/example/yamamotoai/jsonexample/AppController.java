package com.example.yamamotoai.jsonexample;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by yamamotoai on 2017-07-25.
 */

public class AppController extends Application {
    //1.Define your tag and add it to your requests.
    public static final String TAG = AppController.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private static AppController mInstance;

    //setter
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    //getter
    public static synchronized AppController getInstance() {
        return mInstance;
    }

    //need to initialize
    public RequestQueue getRequestQueue() {
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }


}
