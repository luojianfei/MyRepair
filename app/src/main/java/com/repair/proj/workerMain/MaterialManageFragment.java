package com.repair.proj.workerMain;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.repair.proj.R;
import com.repair.proj.base.BaseFragment;
import com.repair.proj.databinding.FragmentMaterialManageBinding;
import com.repair.proj.workerMain.adapter.CustomMaterialManFragmentPagerAdapter;
import com.repair.proj.workerMain.adapter.CustomOrderManFragmentPagerAdapter;
import com.repair.proj.workerMain.presenter.MaterialManagePresenter;

/**
 * Created by HX·罗 on 2017/10/31.
 */

public class MaterialManageFragment extends BaseFragment<MaterialManagePresenter,FragmentMaterialManageBinding>{

    private CustomMaterialManFragmentPagerAdapter pagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pagerAdapter = new CustomMaterialManFragmentPagerAdapter(getChildFragmentManager());
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_material_manage ;
    }

    @Override
    public void initView() {
        viewBinding.vpMaterial.setAdapter(pagerAdapter);
        viewBinding.mdTab.setupWithViewPager(viewBinding.vpMaterial);
    }

    @Override
    public void initData(Bundle arguments) {

    }

    @Override
    public void initListener() {
    }
}
