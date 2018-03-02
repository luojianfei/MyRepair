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
import com.repair.proj.utils.TextUtil;
import com.repair.proj.utils.ValidateUtils;

/**
 * Created by HX·罗 on 2017/10/23.
 */

public class UserLoginPresenter extends NPresenter<UserLoginContract.View,UserLoginModel> implements UserLoginContract.Presenter {

    @Override
    public void login() {
        if(validate()){
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

    /**
     * 验证信息
     * @return
     */
    private boolean validate(){
        String tel = view.getUserName() ;
        String pwd = view.getPwd() ;
        if(TextUtil.isEmpty(tel)) {
            view.showMsg("请输入手机号");
            return false ;
        }else if(ValidateUtils.isMobileNO(tel)){
            view.showMsg("手机号格式错误");
            return false ;
        }else if(TextUtil.isEmpty(pwd)){
            view.showMsg("请输入密码");
            return false ;
        }
        return true ;
    }
}
