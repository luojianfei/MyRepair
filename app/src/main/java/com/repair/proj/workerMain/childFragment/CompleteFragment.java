package com.repair.proj.workerMain.childFragment;

import android.os.Bundle;

import com.repair.proj.R;
import com.repair.proj.base.BaseFragment;
import com.repair.proj.databinding.FragmentCompleteBinding;
import com.repair.proj.entity.OrderInfo;
import com.repair.proj.workerMain.adapter.OrderListAdapter;
import com.repair.proj.workerMain.contract.CompleteContract;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/11/1.
 */

public class CompleteFragment extends BaseFragment<FragmentCompleteBinding> implements CompleteContract.View {
    private OrderListAdapter listAdapter;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_complete ;
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
