package com.repair.proj.userLogin.presenter;

import android.app.Activity;

import com.repair.proj.base.ActivityManager;
import com.repair.proj.base.CustomApplication;
import com.repair.proj.login.contract.RegistFirstContract;
import com.repair.proj.login.model.RegistFirstModel;
import com.repair.proj.nbase.NContract;
import com.repair.proj.nbase.NPresenter;
import com.repair.proj.net.DataResponse;
import com.repair.proj.net.HttpRequest;
import com.repair.proj.userLogin.UserRegistFirstActivity;
import com.repair.proj.userLogin.contract.UserRegistSecondContract;
import com.repair.proj.userLogin.model.UserRegistSecondModel;
import com.repair.proj.utils.ActivityUtils;
import com.repair.proj.utils.MD5;
import com.repair.proj.utils.SPUtils;
import com.repair.proj.utils.TextUtil;
import com.repair.proj.utils.ValidateUtils;

/**
 * Created by HX·罗 on 2017/10/23.
 */

public class UserRegistSecondPresenter extends NPresenter<UserRegistSecondContract.View,UserRegistSecondModel>
        implements UserRegistSecondContract.Presenter {
    @Override
    public void startCompleteRegist() {
        if(validateData()){
            showDialog("请求数据中");
            model.requestUserRegist(context, createTreeMap(new String[]{"username","password","cid","tel"},
                    new String[]{view.getUsername(), MD5.Md5(view.getPwd()), CustomApplication.getClientId(context),view.getTelPhone()}),
                    new HttpRequest.OnNetworkListener<DataResponse>() {
                        @Override
                        public void onSuccess(DataResponse response) {
                            closeDialog();
                            ActivityManager.getInstance().finishActivityForClass(UserRegistFirstActivity.class);
                            view.registSuccess();
                        }

                        @Override
                        public void onFail(String message) {
                            closeDialog() ;
                            view.showMsg(message);
                        }
                    });
        }
    }

    /**
     * 验证数据
     * @return
     */
    private boolean validateData(){
        String username = view.getUsername() ;
        String pwd = view.getPwd() ;
        String surePwd = view.getSurePwd() ;
        if(TextUtil.isEmpty(username)){
            view.showMsg("用户名不能为空");
        }else if(!ValidateUtils.isNickValid(username)){
            view.showMsg("用户名不合法");
        }else if(TextUtil.isEmpty(pwd)){
            view.showMsg("密码不能为空");
        }else if(!pwd.equals(surePwd)){
            view.showMsg("两次输入密码不一致");
        }else if(!ValidateUtils.isPwdValid(pwd)){
            view.showMsg("密码是由6~16位字母和数字组成");
        }else{
            return true ;
        }
        return false ;
    }
}
