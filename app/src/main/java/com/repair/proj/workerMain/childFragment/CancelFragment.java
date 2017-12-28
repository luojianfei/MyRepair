package com.repair.proj.workerMain.childFragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.repair.proj.R;
import com.repair.proj.base.BaseFragment;
import com.repair.proj.databinding.FragmentCancelBinding;
import com.repair.proj.entity.OrderInfo;
import com.repair.proj.workerMain.adapter.OrderListAdapter;
import com.repair.proj.workerMain.contract.CancelContract;
import com.repair.proj.workerMain.presenter.CancelPresenter;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/11/1.
 */

public class CancelFragment extends BaseFragment<CancelPresenter,FragmentCancelBinding> implements CancelContract.View {
    private OrderListAdapter listAdapter;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_cancel;
    }

    @Override
    public void initView() {
        listAdapter = new OrderListAdapter(getContext(),OrderListAdapter.TYPE_ORDER_CANCEL,new ArrayList<OrderInfo>(),null);
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
    public void onClick(View v) {

    }
}
