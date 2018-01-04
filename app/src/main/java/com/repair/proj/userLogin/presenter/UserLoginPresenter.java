package com.repair.proj.userLogin.presenter;

import com.repair.proj.login.contract.LoginContract;
import com.repair.proj.login.model.LoginModel;
import com.repair.proj.nbase.NContract;
import com.repair.proj.nbase.NPresenter;
import com.repair.proj.net.DataResponse;
import com.repair.proj.net.HttpRequest;
import com.repair.proj.userLogin.contract.UserLoginContract;
import com.repair.proj.userLogin.model.UserLoginModel;
import com.repair.proj.utils.MD5;

/**
 * Created by HX·罗 on 2017/10/23.
 */

public class UserLoginPresenter extends NPresenter<UserLoginContract.View,UserLoginModel> implements UserLoginContract.Presenter {

    @Override
    public void login() {
        showDialog("正在登录");
        model.requestUserLogin(context, view.getUserName(), MD5.Md5(view.getPwd()), new HttpRequest.OnNetworkListener<DataResponse>() {
            @Override
            public void onSuccess(DataResponse response) {//登录成功
                closeDialog();
            }

            @Override
            public void onFail(String message) {//登录失败
                closeDialog();
                view.showMsg(message);
            }
        });
    }
}
