package com.repair.proj.utils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


/**
 * Created by Administrator on 2016/8/10 0010.
 */
public class FragmentUtils {


    /**
     * fragment 跳转
     * @param fragmentManager
     * @param from
     * @param to
     */
    public static void gotoNextFragment(FragmentManager fragmentManager, Fragment from, Fragment to, int layoutId){
        FragmentTransaction transaction = fragmentManager.beginTransaction() ;
//        Context context = null ;
//
//        transaction.setCustomAnimations(R.anim.anim_enter,R.anim.anim_exit,R.anim.anim_enter,R.anim.anim_exit) ;
//        transaction.setCustomAnimations(R.anim.anim_enter,R.anim.anim_exit) ;
        if(from != null){
            if(!to.isAdded()){
                transaction.hide(from).add(layoutId,to) ;
                transaction.addToBackStack(to.getTag()) ;
                transaction.commit() ;
            }else{
                transaction.show(to).hide(from).commit() ;
            }
        }else{
            transaction.add(layoutId,to) ;
            transaction.addToBackStack(to.getTag()) ;
            transaction.commit() ;
        }
    }

    public static void goBackFragment(FragmentManager fragmentManager){
        fragmentManager.popBackStack();
    }

}
