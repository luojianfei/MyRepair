package com.repair.proj.nbase;

import android.content.Context;

import com.repair.proj.entity.ValidateInfo;
import com.repair.proj.net.DataResponse;
import com.repair.proj.net.HttpRequest;

import java.util.TreeMap;

/**
 * Created by HX·罗 on 2017/11/14.
 */

public class NModel {

    /**
     * 请求数据
     * @param context
     * @param treeMap
     * @param dataResponse
     * @param listener
     */
    protected static void requestData(Context context,String apiCode, TreeMap<String,String> treeMap, DataResponse dataResponse,
                                   HttpRequest.OnNetworkListener<DataResponse> listener){
        try{
            new HttpRequest<DataResponse>()
                    .with(context)
                    .addParams(treeMap)
                    .setListener(listener)
                    .start(apiCode,dataResponse);
        }catch(Exception e){
            listener.onFail("请求错误");
        }
    }
    /**
     * 获取验证码
     * @param context
     * @param telephone
     * @param listener
     */
    public void getValidateCode(Context context,String telephone, HttpRequest.OnNetworkListener<DataResponse> listener) {
        TreeMap<String,String> params = new TreeMap<>() ;
        params.put("tel",telephone) ;
        requestData(context,ModelConfig.ADDRESS_USER_GET_VALIDATE,params,new DataResponse<ValidateInfo>().init(ValidateInfo.class),listener);
    }

}
