package com.repair.proj.userLogin;

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
            case R.id.btn_login:
                ActivityUtils.startActivityIntent(context,UserLoginActivity.class);
                break;

        }
    }
}
