package com.repair.proj.user;

import android.view.View;

import com.repair.proj.R;
import com.repair.proj.base.BaseActivity;
import com.repair.proj.databinding.ActivityMyWorkerBinding;
import com.repair.proj.user.contract.MyWorkerContract;
import com.repair.proj.user.presenter.MyWorkerPresenter;
import com.repair.proj.utils.Res;

/**
 * 创建人 LEO
 * 创建时间 2017/11/20 11:29
 */

public class MyWorkerActivity extends BaseActivity<MyWorkerPresenter,ActivityMyWorkerBinding> implements MyWorkerContract.View {
    @Override
    public int setContentView() {
        return R.layout.activity_my_worker ;
    }

    @Override
    public void initView() {
        viewBinding.layoutTitle.setRightDrawable(Res.getDrawableRes(R.drawable.more,context));
    }

    @Override
    public void initData() {
        viewBinding.layoutTitle.setTitle("我的师傅");
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
}
