package com.repair.proj.login;

import android.content.Context;
import android.view.View;

import com.repair.proj.R;
import com.repair.proj.base.BaseActivity;
import com.repair.proj.databinding.ActivityRegistFirstBinding;
import com.repair.proj.login.contract.RegistFirstContract;

/**
 * Created by HX·罗 on 2017/10/23.
 */

public class RegistFirstActivity extends BaseActivity<ActivityRegistFirstBinding> implements RegistFirstContract.View {
    @Override
    public int setContentView() {
        return R.layout.activity_regist_first ;
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
    public String getPhoneNo() {
        return viewBinding.getTelphoneNo() ;
    }

    @Override
    public String getValidateCode() {
        return viewBinding.getPwd() ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_next:
                break;

        }
    }

    @Override
    public Context context() {
        return context;
    }

    @Override
    public void showToastMsg(String msg) {
        showShortToast(msg);
    }
}
