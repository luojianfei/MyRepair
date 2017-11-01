package com.repair.proj.login.contract;

import android.app.Activity;

import com.repair.proj.login.entity.SecondEnitity;
import com.repair.proj.nbase.NContract;


/**
 * 说明：
 * Created by code_nil on 2017/10/27.
 */

public interface RegistSecondContract {
    interface View extends NContract.View {
        void setLocationData(String data);

        SecondEnitity getRegistSecondData();
    }

    interface Presenter extends NContract.Presenter {
        void showLocationPicker(Activity activity);
    }

    interface Model extends NContract.Model {

    }
}
