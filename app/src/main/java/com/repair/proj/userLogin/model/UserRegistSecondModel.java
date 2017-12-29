package com.repair.proj.userLogin.model;

import android.content.Context;

import com.repair.proj.nbase.ModelConfig;
import com.repair.proj.nbase.NModel;
import com.repair.proj.net.DataResponse;
import com.repair.proj.net.HttpRequest;
import com.repair.proj.userLogin.contract.UserRegistSecondContract;

import java.util.TreeMap;

/**
 * 说明：
 * Created by code_nil on 2017/10/27.
 */

public class UserRegistSecondModel extends NModel implements UserRegistSecondContract.Model {
    /**
     * 用户注册
     * @param context
     * @param params
     * @param listener
     */
    @Override
    public void requestUserRegist(Context context, TreeMap<String, String> params, HttpRequest.OnNetworkListener<DataResponse> listener) {
        requestData(context, ModelConfig.ADDRESS_USER_REGIST,params,new DataResponse(),listener);
    }
}
