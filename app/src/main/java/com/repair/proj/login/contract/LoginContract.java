package com.repair.proj.login.contract;

import com.repair.proj.base.Basefview;

/**
 * Created by HX·罗 on 2017/10/23.
 */

public interface LoginContract {
    interface View extends Basefview {
        String getUserName();

        String getPwd();
    }

    interface Presenter {
        void method();

        void login();

        void regist();

        void forgetPwd();
    }

    interface Model {
        void method();
    }
}
