package com.repair.proj.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.baidu.mapapi.SDKInitializer;
import com.repair.proj.utils.SPUtils;

/**
 * Created by HX·罗 on 2017/6/2.
 */

public class CustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this) ;
    }
    public static String getClientId(Context context){
        return SPUtils.get(context,"ClientInfo","clientId","").toString();
    }
}
