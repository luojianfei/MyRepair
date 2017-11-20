package com.repair.proj.userLogin.contract;

import com.repair.proj.nbase.NContract;

/**
 * Created by HX·罗 on 2017/10/23.
 */

public interface UserRegistFirstContract {
    interface View extends NContract.View {
        String getPhoneNo();

        String getValidateCode();
    }

    interface Presenter extends NContract.Presenter{
        void requestValidateCode();

        void next();
    }

    interface Model extends NContract.Model{
    }
}
