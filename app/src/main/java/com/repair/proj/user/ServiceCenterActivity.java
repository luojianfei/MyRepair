package com.repair.proj.user;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.repair.proj.R;
import com.repair.proj.base.BaseActivity;
import com.repair.proj.databinding.ActivityServiceCenterBinding;
import com.repair.proj.utils.Res;

/**
 * 创建人 LEO
 * 创建时间 2017/11/20 14:23
 */

public class ServiceCenterActivity extends BaseActivity {

    private ActivityServiceCenterBinding viewBinding;

    @Override
    public int setContentView() {
        return R.layout.activity_service_center;
    }

    @Override
    public void initView() {
        viewBinding = DataBindingUtil.bind(parentView);
        viewBinding.layoutTitle.setRightDrawable(Res.getDrawableRes(R.drawable.more,context));
    }

    @Override
    public void initData() {
        viewBinding.layoutTitle.setTitle("客服中心");
    }

    @Override
    public void initListener() {
        viewBinding.layoutTitle.setClickListener(this);
        viewBinding.setClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_back:
                finish();
                break ;
            case R.id.iv_right:
                break;
            case R.id.tv_nomal_question://常见问题
                break;
                case R.id.tv_order_question://订单问题
                break;
                case R.id.tv_system_return://系统回馈
                break;
                case R.id.tv_opinion://意见反馈
                break;
            default:
                break ;
        }
    }
}
