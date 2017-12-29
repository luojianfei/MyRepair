package com.repair.proj.userLogin;

import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.repair.proj.R;
import com.repair.proj.base.BaseActivity;
import com.repair.proj.databinding.ActivityUserLoginBinding;
import com.repair.proj.user.UserDetailActivity;
import com.repair.proj.userLogin.contract.UserLoginContract;
import com.repair.proj.userLogin.presenter.UserLoginPresenter;
import com.repair.proj.utils.ActivityUtils;

/**
 * Created by HX·罗 on 2017/10/23.
 */

public class UserLoginActivity extends BaseActivity<UserLoginPresenter,ActivityUserLoginBinding> implements UserLoginContract.View {
    @Override
    public int setContentView() {
        return R.layout.activity_user_login;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {
        viewBinding.setEyeState(1) ;
        viewBinding.layoutTitle.setTitle("登录");
    }

    @Override
    public void initListener() {
        viewBinding.layoutTitle.setClickListener(this);
        viewBinding.setClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back://返回
                finish();
                break;
            case R.id.iv_eye://查看密码
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
            case R.id.btn_login://登录
                ActivityUtils.startActivityIntent(context, UserDetailActivity.class);
                break;
            case R.id.tv_fogot://忘记密码
                break;
            case R.id.tv_regist_repair://注册成师傅
                ActivityUtils.startActivityIntent(context,UserRegistFirstActivity.class);
                break;
        }
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String getPwd() {
        return null;
    }

    @Override
    public Context context() {
        return context;
    }

    @Override
    public void showMsg(String msg) {
        showShortToast(msg);
    }
}
