package com.repair.proj.workerMain.childFragment;

import android.os.Bundle;

import com.repair.proj.R;
import com.repair.proj.base.BaseFragment;
import com.repair.proj.databinding.FragmentCancelBinding;
import com.repair.proj.entity.OrderInfo;
import com.repair.proj.workerMain.adapter.OrderListAdapter;
import com.repair.proj.workerMain.contract.CancelContract;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/11/1.
 */

public class CancelFragment extends BaseFragment<FragmentCancelBinding> implements CancelContract.View {
    private OrderListAdapter listAdapter;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_cancel;
    }

    @Override
    public void initView() {
        listAdapter = new OrderListAdapter(getContext(),new ArrayList<OrderInfo>(),null);
        viewBinding.rcvList.setAdapter(listAdapter);
    }

    @Override
    public void initData(Bundle arguments) {

    }

    @Override
    public void initListener() {

    }
}
