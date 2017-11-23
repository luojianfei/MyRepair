package com.sdk.getui;

import android.content.Context;
import android.util.Log;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;

/**
 * 创建人 LEO
 * 创建时间 2017/11/23 11:44
 */

public class GTPushService extends GTIntentService {
    @Override
    public void onReceiveServicePid(Context context, int pid) {
        System.out.print("onReceiveServicePid");
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        System.out.print("onReceiveMessageData");
    }

    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.e(TAG, "onReceiveClientId -> " + "clientid = " + clientid);
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
        System.out.print("onReceiveClientId");
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
        System.out.print("onReceiveCommandResult");
    }
}
