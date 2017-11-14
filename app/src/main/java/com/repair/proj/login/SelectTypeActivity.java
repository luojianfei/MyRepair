package com.repair.proj.login;

import android.os.Bundle;
import android.view.View;

import com.repair.proj.R;
import com.repair.proj.base.BaseActivity;
import com.repair.proj.databinding.ActivitySelectTypeBinding;
import com.repair.proj.maindetail.MainDetailActivity;
import com.repair.proj.utils.ActivityUtils;

/**
 * Created by HX·罗 on 2017/10/20.
 */

public class SelectTypeActivity extends BaseActivity<ActivitySelectTypeBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public int setContentView() {
        return R.layout.activity_select_type;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        viewBinding.setClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_repair://维修师傅
                ActivityUtils.startActivityIntent(context,LoginActivity.class);
                break;
            case R.id.tv_need_repair://用户报修
                ActivityUtils.startActivityIntent(context,MainDetailActivity.class);
                break;
        }
    }
}
