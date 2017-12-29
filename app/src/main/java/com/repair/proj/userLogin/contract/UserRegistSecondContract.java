package com.repair.proj.userLogin.contract;

import android.app.Activity;
import android.content.Context;

import com.repair.proj.login.entity.SecondEnitity;
import com.repair.proj.nbase.NContract;
import com.repair.proj.net.DataResponse;
import com.repair.proj.net.HttpRequest;

import java.util.TreeMap;


/**
 * 说明：
 * Created by code_nil on 2017/10/27.
 */

public interface UserRegistSecondContract {
    interface View extends NContract.View {
        String getUsername();

        String getPwd();

        String getSurePwd();

        String getValidateCode();

        void registSuccess();
    }

    interface Presenter extends NContract.Presenter {
        void startCompleteRegist();
    }

    interface Model extends NContract.Model {
        void requestUserRegist(Context context, TreeMap<String,String> params, HttpRequest.OnNetworkListener<DataResponse> listener) ;
    }
}
