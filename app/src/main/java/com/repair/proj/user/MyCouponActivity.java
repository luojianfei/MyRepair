package com.repair.proj.user;

import android.content.Context;
import android.view.View;

import com.repair.proj.R;
import com.repair.proj.base.BaseActivity;
import com.repair.proj.databinding.ActivityCouponBinding;
import com.repair.proj.user.contract.MyCouponContract;
import com.repair.proj.user.presenter.MyCouponPresenter;
import com.repair.proj.utils.Res;

/**
 * 创建人 LEO
 * 创建时间 2017/11/20 12:03
 */

public class MyCouponActivity extends BaseActivity<MyCouponPresenter,ActivityCouponBinding> implements MyCouponContract.View {
    @Override
    public int setContentView() {
        return R.layout.activity_coupon ;
    }
    @Override
    public void initView() {
        viewBinding.layoutTitle.setRightDrawable(Res.getDrawableRes(R.drawable.more,context));
    }

    @Override
    public void initData() {
        viewBinding.layoutTitle.setTitle("修修券");
    }

    @Override
    public void initListener() {
        viewBinding.layoutTitle.setClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_back:
                finish();
                break ;
            case R.id.iv_right:
                break;
            default:
                break ;
        }
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
