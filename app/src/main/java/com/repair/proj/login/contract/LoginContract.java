package com.repair.proj.login.contract;

import com.repair.proj.nbase.NContract;

/**
 * Created by HX·罗 on 2017/10/23.
 */

public interface LoginContract {
    interface View extends NContract.View {
        String getUserName();

        String getPwd();
    }

    interface Presenter extends NContract.Presenter {
        void method();

        void login();

        void regist();

        void forgetPwd();
    }

    interface Model extends NContract.Model{
        void method();
    }
}
