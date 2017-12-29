package com.repair.proj.user;

import android.content.Context;
import android.view.View;

import com.repair.proj.R;
import com.repair.proj.base.BaseActivity;
import com.repair.proj.databinding.ActivityInviteRewardBinding;
import com.repair.proj.user.contract.InviteRewardContract;
import com.repair.proj.user.presenter.InviteRewardPresenter;
import com.repair.proj.utils.Res;

/**
 * 创建人 LEO
 * 创建时间 2017/11/20 16:02
 */

public class InviteRewardActivity extends BaseActivity<InviteRewardPresenter,ActivityInviteRewardBinding> implements InviteRewardContract.View {

    @Override
    public int setContentView() {
       return R.layout.activity_invite_reward ;
    }

    @Override
    public void initView() {
        viewBinding.layoutTitle.setRightDrawable(Res.getDrawableRes(R.drawable.more,context));
    }

    @Override
    public void initData() {
        viewBinding.layoutTitle.setTitle("邀请有奖");
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
                break;
            case R.id.iv_right:
                break;
            case R.id.btn_invite:
                break ;
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
