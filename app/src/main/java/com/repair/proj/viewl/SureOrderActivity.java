package com.repair.proj.viewl;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.repair.proj.R;
import com.repair.proj.base.BaseActivity;
import com.repair.proj.databinding.ActivitySureOrderBinding;

/**
 * 确定订单
 * Created by HX·罗 on 2017/10/18.
 */

public class SureOrderActivity extends BaseActivity<ActivitySureOrderBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        color = Color.parseColor("#555555");
        super.onCreate(savedInstanceState);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_sure_order;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        viewBinding.layoutTitle.setTitle("确认订单");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }
}