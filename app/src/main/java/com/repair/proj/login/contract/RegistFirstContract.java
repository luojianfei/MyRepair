package com.repair.proj.login.contract;

import com.repair.proj.nbase.NContract;

/**
 * Created by HX·罗 on 2017/10/23.
 */

public interface RegistFirstContract {
    interface View extends NContract.View {
        String getPhoneNo();

        String getValidateCode();

        void startTimeDown();

        void endTimeDown();

        void setTimeDown(int time);
    }

    interface Presenter extends NContract.Presenter{
        void requestValidateCode();

        void next();
    }

    interface Model extends NContract.Model{
    }
}
