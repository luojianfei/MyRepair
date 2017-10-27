package com.repair.proj.login;

import com.repair.proj.R;
import com.repair.proj.databinding.ActivityRegistThreeBinding;
import com.repair.proj.login.contract.RegistThreeContract;
import com.repair.proj.login.presenter.RegistThreePresenter;
import com.repair.proj.nbase.NActivity;

/**
 * 说明：
 * Created by code_nil on 2017/10/27.
 */

public class RegistThreeActivity extends NActivity<RegistThreePresenter,ActivityRegistThreeBinding> implements RegistThreeContract.View {
    @Override
    protected int getContentId() {
        return R.layout.activity_regist_three;
    }

    @Override
    public void method() {

    }
}
