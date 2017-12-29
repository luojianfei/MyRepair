package com.repair.proj.net;

import android.text.TextUtils;
import android.util.Log;

import com.repair.proj.utils.TextUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jniu on 2017/5/17.
 */

public abstract class BaseResponse {

    public String code;
    public String msg;
    public String data;

    public void parseJson(String json) {
        Log.e("json", "json --- " + json);
        try {
            if (TextUtils.isEmpty(json)) {
                return;
            }
            JSONObject object = new JSONObject(json);
            code = object.optString("code");
            if ("200".equals(code)) {//请求成功
                String info;
                if (object.optJSONObject("data") != null) {
                    info = object.optJSONObject("data").toString();
                    parseInfo(info);
                } else if (object.optJSONArray("data") != null) {
                    info = object.optJSONArray("data").toString();
                    parseInfo(info);
                } else {
                    this.data = object.optString("data");
                }
            } else {
                msg = object.optString("msg");
                if(TextUtil.isEmpty(msg)){
                    msg = "访问服务器失败，请稍后再试" ;
                }
            }
        } catch (JSONException e) {
            Log.e("BaseResponse", "json格式有误:" + json );
        }
    }

    public abstract void parseInfo(String content) throws JSONException;
}
