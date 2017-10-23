package com.repair.proj.login.contract;

import com.repair.proj.base.Basefview;

/**
 * Created by HX·罗 on 2017/10/23.
 */

public interface RegistFirstContract {
    interface View extends Basefview {
        String getPhoneNo();

        String getValidateCode();
    }

    interface Presenter {
        void requestValidateCode();

        void next();
    }

    interface Model {
    }
}
