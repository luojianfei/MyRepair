package com.repair.proj.userLogin.model;

import android.content.Context;

import com.repair.proj.login.contract.RegistFirstContract;
import com.repair.proj.nbase.ModelConfig;
import com.repair.proj.nbase.NModel;
import com.repair.proj.net.DataResponse;
import com.repair.proj.net.HttpRequest;
import com.repair.proj.net.HttpRequest.OnNetworkListener;
import com.repair.proj.userLogin.contract.UserRegistFirstContract;

import java.util.TreeMap;

/**
 * Created by HX·罗 on 2017/10/23.
 */

public class UserRegistFirstModel extends NModel implements UserRegistFirstContract.Model {


    /**
     * 验证
     * @param context
     * @param params
     * @param listener
     */
    @Override
    public void requestToValidate(Context context, TreeMap<String, String> params, OnNetworkListener<DataResponse> listener) {
        requestData(context, ModelConfig.ADDRESS_USER_REQUEST_TO_VALIDATE,params,new DataResponse(),listener);
    }
}
