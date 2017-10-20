package com.repair.proj.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;


/**
 * Created by Administrator on 2016/8/4 0004.
 */
public class ActivityUtils {

    private static Intent intent ;
    private static Intent getIntent(){
        if (intent == null){
            intent = new Intent() ;
        }
        return  intent ;
    }

    /**
     * activity页面跳转
     * @param context
     * @param clazz
     */
    public static void startActivityIntent(Context context, Class clazz){
        Activity activity = (Activity) context ;
        Intent intent = getIntent() ;
        intent.setClass(context,clazz) ;
        context.startActivity(intent);
    }/**
     * activity页面跳转
     * @param context
     * @param clazz
     * @param bundle
     */
    public static void startActivityIntent(Context context, Class clazz, Bundle bundle){
        Intent intent = getIntent() ;
        intent.setClass(context,clazz) ;
        if(bundle != null){
            intent.putExtras(bundle) ;
        }
        context.startActivity(intent);
    }

    /**
     * activity页面跳转返回
     * @param requestCode
     */

    public static void startActivityForResult(Context context, Intent intent, int requestCode) {
        Activity activity = (Activity) context ;
        activity.startActivityForResult(intent,requestCode);
    }

    /**
     * 通过通讯录选择联系人
     * @param activity
     */
    public static void startActivityForContacts(Activity activity){
        Intent intent = null ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setClassName("com.android.contacts", "com.android.contacts.activities.ContactSelectionActivity");
        } else {
            intent = new Intent(Intent.ACTION_PICK,
                    ContactsContract.Contacts.CONTENT_URI);
        }
        activity.startActivityForResult(intent, 1);
    }

}
