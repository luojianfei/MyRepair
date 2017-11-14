package com.repair.proj.workerMain;

import android.os.Bundle;

import com.repair.proj.R;
import com.repair.proj.base.BaseFragment;
import com.repair.proj.databinding.FragmentMaterialManageBinding;
import com.repair.proj.workerMain.presenter.MaterialManagePresenter;

/**
 * Created by HX·罗 on 2017/10/31.
 */

public class MaterialManageFragment extends BaseFragment<MaterialManagePresenter,FragmentMaterialManageBinding>{
    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_material_manage ;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle arguments) {

    }

    @Override
    public void initListener() {

    }
}
