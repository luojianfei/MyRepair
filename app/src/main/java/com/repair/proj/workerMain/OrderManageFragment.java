package com.repair.proj.workerMain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.repair.proj.R;
import com.repair.proj.base.BaseFragment;
import com.repair.proj.databinding.FragmentOrderManageBinding;
import com.repair.proj.workerMain.adapter.CustomOrderManFragmentPagerAdapter;
import com.repair.proj.workerMain.contract.OrderManageContract;
import com.repair.proj.workerMain.presenter.OrderManagePresenter;

/**
 * Created by HX·罗 on 2017/10/31.
 */

public class OrderManageFragment extends BaseFragment<OrderManagePresenter,FragmentOrderManageBinding> implements OrderManageContract.View {

    private CustomOrderManFragmentPagerAdapter pagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pagerAdapter = new CustomOrderManFragmentPagerAdapter(getChildFragmentManager());
    }

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_order_manage ;
    }

    @Override
    public void initView() {
        viewBinding.vpOrder.setAdapter(pagerAdapter);
        viewBinding.mdTab.setupWithViewPager(viewBinding.vpOrder);
    }

    @Override
    public void initData(Bundle arguments) {

    }

    @Override
    public void initListener() {
    }

    @Override
    public void onClick(View v) {

    }
}
