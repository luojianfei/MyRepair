package com.repair.proj.workerMain.childFragment;

import android.os.Bundle;

import com.repair.proj.R;
import com.repair.proj.base.BaseFragment;
import com.repair.proj.databinding.FragmentConductBinding;
import com.repair.proj.entity.OrderInfo;
import com.repair.proj.workerMain.adapter.OrderListAdapter;
import com.repair.proj.workerMain.contract.ConductContract;
import com.repair.proj.workerMain.presenter.ConductPresenter;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/11/1.
 */

public class ConductFragment extends BaseFragment<ConductPresenter,FragmentConductBinding> implements ConductContract.View,OrderListAdapter.CallBack {

    private OrderListAdapter listAdapter;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_conduct ;
    }

    @Override
    public void initView() {
        listAdapter = new OrderListAdapter(getContext(),new ArrayList<OrderInfo>(),this);
        viewBinding.rcvList.setAdapter(listAdapter);
    }

    @Override
    public void initData(Bundle arguments) {
    }

    @Override
    public void initListener() {

    }

    @Override
    public void callBack(int position) {
//        ActivityUtils.startActivityIntent(getContext(), SureOrderActivity.class);
    }
}
