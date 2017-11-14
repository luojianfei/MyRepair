package com.repair.proj.user.contract;


import com.repair.proj.nbase.NContract;

/**
 * 说明：
 * Created by code_nil on 2017/10/23.
 */

public interface UserDetailContract {

    interface View extends NContract.View {
    }

    interface Presenter extends NContract.Presenter {
        void showOrDismissDetailPicker(Boolean isShow);
    }

    interface Model extends NContract.Model {
    }


}
