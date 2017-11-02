package com.repair.proj.maindetail.contract;


import com.repair.proj.nbase.NContract;

/**
 * 说明：
 * Created by code_nil on 2017/10/23.
 */

public interface MainDetailContract {

    interface View extends NContract.View {
    }

    interface Presenter extends NContract.Presenter {
        void showOrDismissDetailPicker(Boolean isShow);

    }

    interface Model extends NContract.Model {
    }


}
