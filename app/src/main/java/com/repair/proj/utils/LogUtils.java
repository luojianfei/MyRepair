package com.repair.proj.utils;

import android.content.Context;
import android.util.Log;

/**
 * Created by Administrator on 2016/8/29 0029.
 */
public class LogUtils {
    public static void _v(String msg, Context context){
        Log.v(AppUtils.getAppName(context),msg) ;
    }
}
