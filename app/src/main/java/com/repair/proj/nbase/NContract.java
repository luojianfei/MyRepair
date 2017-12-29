package com.repair.proj.nbase;

import android.content.Context;

/**
 * 说明：
 * Created by code_nil on 2017/10/27.
 */

public interface NContract {
    interface Presenter  {

    }

    interface View {

        Context context() ;
        void showMsg(String msg) ;

    }

    interface Model {

    }
}