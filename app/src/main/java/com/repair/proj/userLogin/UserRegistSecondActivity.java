package com.repair.proj.userLogin;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.repair.proj.R;
import com.repair.proj.base.BaseActivity;
import com.repair.proj.databinding.ActivityRegistFirstBinding;
import com.repair.proj.databinding.ActivityUserRegistSecondBinding;
import com.repair.proj.login.RegistSecondActivity;
import com.repair.proj.login.contract.RegistFirstContract;
import com.repair.proj.login.contract.RegistSecondContract;
import com.repair.proj.login.presenter.RegistFirstPresenter;
import com.repair.proj.login.presenter.RegistSecondPresenter;
import com.repair.proj.userLogin.contract.UserRegistSecondContract;
import com.repair.proj.userLogin.presenter.UserRegistSecondPresenter;
import com.repair.proj.utils.ActivityUtils;

/**
 * Created by HX·罗 on 2017/10/23.
 */

public class UserRegistSecondActivity extends BaseActivity<UserRegistSecondPresenter,ActivityUserRegistSecondBinding> implements UserRegistSecondContract.View {
    @Override
    public int setContentView() {
        return R.layout.activity_user_regist_second ;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        viewBinding.setEyeState(1);
        viewBinding.setEyeState1(1);
        viewBinding.layoutTitle.setTitle("注册");
    }

    @Override
    public void initListener() {
        viewBinding.setClickListener(this);
        viewBinding.layoutTitle.setClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_eye:
                viewBinding.setEyeState(1 - viewBinding.getEyeState());
                if (viewBinding.getEyeState() == 1) {//隐藏密码
                    viewBinding.etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {//显示密码
                    viewBinding.etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                try {
                    viewBinding.etPwd.setSelection(viewBinding.getPwd().length());//光标移动到最后位置
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.iv_eye_agin:
                viewBinding.setEyeState1(1 - viewBinding.getEyeState1());
                if (viewBinding.getEyeState1() == 1) {//隐藏密码
                    viewBinding.etPwdSure.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {//显示密码
                    viewBinding.etPwdSure.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                try {
                    viewBinding.etPwdSure.setSelection(viewBinding.getPwdSure().length());//光标移动到最后位置
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_login:
                ActivityUtils.startActivityIntent(context,UserLoginActivity.class);
                break;

        }
    }
}
