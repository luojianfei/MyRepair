package com.repair.proj.userLogin.model;

import android.content.Context;

import com.repair.proj.base.CustomApplication;
import com.repair.proj.entity.UserInfo;
import com.repair.proj.login.contract.LoginContract;
import com.repair.proj.nbase.ModelConfig;
import com.repair.proj.nbase.NModel;
import com.repair.proj.net.DataResponse;
import com.repair.proj.net.HttpRequest;
import com.repair.proj.net.HttpRequest.OnNetworkListener;
import com.repair.proj.userLogin.contract.UserLoginContract;

import java.util.TreeMap;

/**
 * Created by HX·罗 on 2017/10/23.
 */

public class UserLoginModel extends NModel implements UserLoginContract.Model {
    @Override
    public void requestUserLogin(Context context, String telephone, String pwd, HttpRequest.OnNetworkListener<DataResponse> listener) {
        TreeMap<String,String> params = new TreeMap<>() ;
        params.put("username",telephone) ;
        params.put("password",pwd) ;
        params.put("cid", CustomApplication.getClientId(context)) ;
        requestData(context, ModelConfig.ADDRESS_USER_LOGIN,params,new DataResponse<UserInfo>().init(UserInfo.class),listener);
    }
}
