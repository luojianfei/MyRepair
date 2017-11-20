package com.repair.proj.user;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.repair.proj.R;
import com.repair.proj.base.BaseActivity;
import com.repair.proj.databinding.ActivityMoreSettingBinding;
import com.repair.proj.utils.Res;

/**
 * 创建人 LEO
 * 创建时间 2017/11/20 15:11
 */

public class MoreSettingActivity extends BaseActivity {

    private ActivityMoreSettingBinding binding;

    @Override
    public int setContentView() {
        return R.layout.activity_more_setting;
    }

    @Override
    public void initView() {
        binding = DataBindingUtil.bind(parentView);
        binding.layoutTitle.setRightDrawable(Res.getDrawableRes(R.drawable.more,context));
    }

    @Override
    public void initData() {
        binding.layoutTitle.setTitle("更多设置");
    }

    @Override
    public void initListener() {
        binding.layoutTitle.setClickListener(this);
        binding.setClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_back:
                finish();
                break ;
            case R.id.iv_right:
                break;
            case R.id.tv_logout://注销
                break;
            case R.id.tv_user_rule://用户守则
                break;
            case R.id.tv_charge_standard://收费标准
                break;
            case R.id.tv_share://分享
                break;
            case R.id.tv_score://评价
                break;
            case R.id.tv_about://关于app
                break;
            default:
                break ;
        }
    }
}
