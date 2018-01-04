package com.repair.proj.userLogin.presenter;

import android.app.AlertDialog;

import com.repair.proj.entity.ValidateInfo;
import com.repair.proj.login.contract.RegistFirstContract;
import com.repair.proj.login.model.RegistFirstModel;
import com.repair.proj.nbase.NContract;
import com.repair.proj.nbase.NPresenter;
import com.repair.proj.net.BaseResponse;
import com.repair.proj.net.DataResponse;
import com.repair.proj.net.HttpRequest;
import com.repair.proj.userLogin.contract.UserRegistFirstContract;
import com.repair.proj.userLogin.model.UserRegistFirstModel;
import com.repair.proj.utils.DialogUtils;
import com.repair.proj.utils.RxCountdown;
import com.repair.proj.utils.TextUtil;
import com.repair.proj.utils.ValidateUtils;

import java.util.Observable;

import rx.Subscriber;
import rx.functions.Action0;

/**
 * Created by HX·罗 on 2017/10/23.
 */

public class UserRegistFirstPresenter extends NPresenter<UserRegistFirstContract.View, UserRegistFirstModel>
        implements UserRegistFirstContract.Presenter, HttpRequest.OnNetworkListener<DataResponse> {


    private String telPhone = "";
    private ValidateInfo validateInfo = null;

    @Override
    public void requestValidateCode() {
        telPhone = view.getPhoneNo();
        if (ValidateUtils.isMobileNO(telPhone)) {
            showDialog("");
            model.getValidateCode(view.context(), telPhone, this);
        } else {
            view.showMsg("手机号码错误");
        }
    }

    private void setValidateCodeShow(int time, final String format) {
        RxCountdown.countdown(time)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        view.startTimeDown();
                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        view.endTimeDown();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        view.setTimeDown(integer, format);
                    }
                });
    }

    @Override
    public void next() {
        if (isValid()) {
            showDialog("请求数据中");
            String id = validateInfo != null?validateInfo.getId():"" ;
            model.requestToValidate(context, createTreeMap(new String[]{"code", "tel","id"},
                    new String[]{view.getValidateCode(), view.getPhoneNo(),id}),
                    new HttpRequest.OnNetworkListener<DataResponse>() {
                        @Override
                        public void onSuccess(DataResponse response) {
                            closeDialog();
                            view.validateSuccess(telPhone);
                        }

                        @Override
                        public void onFail(String message) {
                            if (TextUtil.isEmpty(message))
                                message = "验证失败";
                            closeDialog();
                            view.showMsg(message);
                        }
                    });
        }
    }

    /**
     * 验证数据是否合法
     *
     * @return
     */
    private boolean isValid() {
        if (!ValidateUtils.isMobileNO(view.getPhoneNo())) {
            view.showMsg("手机号错误");
        } else if (!ValidateUtils.isValidCode(view.getValidateCode())) {
            view.showMsg("验证码格式不对");
        } else {
            return true;
        }
        return false;
    }

    @Override
    public void onSuccess(DataResponse response) {
        try {
            this.validateInfo = (ValidateInfo) response.info;
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeDialog();
        setValidateCodeShow(60, "%ds重新获取");
        view.showMsg("获取验证码成功，请及时使用");
    }

    @Override
    public void onFail(String message) {
        closeDialog();
        setValidateCodeShow(3, "获取失败(%ds)");
        view.showMsg("获取验证码失败");
    }
}
