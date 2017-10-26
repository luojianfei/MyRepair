package com.repair.proj.base;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by HX·罗 on 2017/6/2.
 */

public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
    }
}
