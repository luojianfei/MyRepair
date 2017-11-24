package com.repair.proj.userLogin;

import android.view.View;

import com.repair.proj.R;
import com.repair.proj.base.BaseActivity;
import com.repair.proj.databinding.ActivityRegistFirstBinding;
import com.repair.proj.databinding.ActivityUserRegistFirstBinding;
import com.repair.proj.login.RegistSecondActivity;
import com.repair.proj.login.contract.RegistFirstContract;
import com.repair.proj.login.presenter.RegistFirstPresenter;
import com.repair.proj.userLogin.contract.UserRegistFirstContract;
import com.repair.proj.userLogin.presenter.UserRegistFirstPresenter;
import com.repair.proj.utils.ActivityUtils;

/**
 * Created by HX·罗 on 2017/10/23.
 */

public class UserRegistFirstActivity extends BaseActivity<UserRegistFirstPresenter,ActivityUserRegistFirstBinding> implements UserRegistFirstContract.View {
    @Override
    public int setContentView() {
        return R.layout.activity_user_regist_first ;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        viewBinding.setValidateShow("获取验证码");
        viewBinding.layoutTitle.setTitle("注册");
    }

    @Override
    public void initListener() {
        viewBinding.setClickListener(this);
        viewBinding.layoutTitle.setClickListener(this);
    }

    @Override
    public String getPhoneNo() {
        return viewBinding.getPhoneNo() ;
    }

    @Override
    public String getValidateCode() {
        return viewBinding.getValidateCode() ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_validate:
                presenter.requestValidateCode();
                break;
            case R.id.btn_next:
                ActivityUtils.startActivityIntent(context,UserRegistSecondActivity.class);
                break;

        }
    }

    @Override
    public void startTimeDown() {
        viewBinding.btnValidate.setEnabled(false);
    }

    @Override
    public void setTimeDown(int time) {
        viewBinding.setValidateShow(time+"s重新获取");
    }

    @Override
    public void endTimeDown() {
        viewBinding.btnValidate.setEnabled(true);
        viewBinding.setValidateShow("获取验证码");
    }
}
