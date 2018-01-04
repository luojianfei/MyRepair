package com.repair.proj.userLogin.contract;

import android.content.Context;

import com.repair.proj.nbase.NContract;
import com.repair.proj.net.DataResponse;
import com.repair.proj.net.HttpRequest;
import com.repair.proj.net.HttpRequest.OnNetworkListener;

/**
 * Created by HX·罗 on 2017/10/23.
 */

public interface UserLoginContract {
    interface View extends NContract.View {
        String getUserName();

        String getPwd();

    }

    interface Presenter extends NContract.Presenter {

        void login();

    }

    interface Model extends NContract.Model{
        void requestUserLogin(Context context, String telephone, String pwd, HttpRequest.OnNetworkListener<DataResponse> listener);
    }
}
