package com.repair.proj.workerMain.childFragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.repair.proj.R;
import com.repair.proj.base.BaseFragment;
import com.repair.proj.databinding.FragmentCompleteBinding;
import com.repair.proj.entity.OrderInfo;
import com.repair.proj.order.SureOrderActivity;
import com.repair.proj.utils.ActivityUtils;
import com.repair.proj.workerMain.adapter.OrderListAdapter;
import com.repair.proj.workerMain.contract.CompleteContract;
import com.repair.proj.workerMain.presenter.CompletePresenter;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/11/1.
 */

public class CompleteFragment extends BaseFragment<CompletePresenter,FragmentCompleteBinding> implements CompleteContract.View ,OrderListAdapter.CallBack{
    private OrderListAdapter listAdapter;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_complete ;
    }

    @Override
    public void initView() {
        listAdapter = new OrderListAdapter(getContext(),OrderListAdapter.TYPE_ORDER_COMPLETE,new ArrayList<OrderInfo>(),null);
        viewBinding.rcvList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(0, 0, 0, 20);
            }
        });
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
        ActivityUtils.startActivityIntent(getContext(), SureOrderActivity.class);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void showMsg(String msg) {

    }
}
