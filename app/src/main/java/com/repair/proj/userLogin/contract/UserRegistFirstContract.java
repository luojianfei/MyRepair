package com.repair.proj.userLogin.contract;

import android.content.Context;

import com.repair.proj.nbase.NContract;
import com.repair.proj.net.DataResponse;
import com.repair.proj.net.HttpRequest;
import com.repair.proj.net.HttpRequest.OnNetworkListener;

import java.util.TreeMap;

/**
 * Created by HX·罗 on 2017/10/23.
 */

public interface UserRegistFirstContract {
    interface View extends NContract.View {
        String getPhoneNo();

        String getValidateCode();

        void startTimeDown();

        void setTimeDown(int time);

        void endTimeDown();
        Context context() ;
        void showMsg(String msg);
        void validateSuccess() ;
    }

    interface Presenter extends NContract.Presenter{
        void requestValidateCode();

        void next();
    }

    interface Model extends NContract.Model{
        void requestToValidate(Context context, TreeMap<String,String> params, HttpRequest.OnNetworkListener<DataResponse> listener) ;
    }
}
