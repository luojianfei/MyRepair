package com.repair.proj.viewl;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.repair.proj.R;
import com.repair.proj.base.BaseActivity;
import com.repair.proj.databinding.ActivitySureOrderBinding;
import com.repair.proj.utils.ActivityUtils;
import com.repair.proj.utils.ConstantUtil;
import com.repair.proj.utils.PhoneUtils;

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
        viewBinding.layoutTitle.setClickListener(this);
        viewBinding.setClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_telephone_book://电话本选择联系人
                ActivityUtils.startActivityForContacts(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ConstantUtil.REQUEST_CODE_GETCONTACTS://获取联系人
                viewBinding.setContactInfo(PhoneUtils.getPhoneNumber(context,data));
                break;
        }
    }


}
