package com.repair.proj.workerMain.childFragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.repair.proj.R;
import com.repair.proj.base.BaseFragment;
import com.repair.proj.databinding.FragmentMatarialPurchBinding;
import com.repair.proj.entity.MaterialInfo;
import com.repair.proj.material.SearchFragmentActivity;
import com.repair.proj.order.SureOrderActivity;
import com.repair.proj.utils.ActivityUtils;
import com.repair.proj.workerMain.adapter.MaterialAdapter;
import com.repair.proj.workerMain.adapter.OrderListAdapter;
import com.repair.proj.workerMain.contract.CompleteContract;
import com.repair.proj.workerMain.presenter.CompletePresenter;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/11/1.
 */

public class PurchasingFragment extends BaseFragment<CompletePresenter,FragmentMatarialPurchBinding> implements CompleteContract.View ,OrderListAdapter.CallBack{
    private MaterialAdapter listAdapter;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_matarial_purch;
    }

    @Override
    public void initView() {
        listAdapter = new MaterialAdapter(getContext(), MaterialAdapter.TYPE_MATERIAL_PURCHAS,new ArrayList<MaterialInfo>(),null);
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
        viewBinding.setClickListener(this);
    }

    @Override
    public void callBack(int position) {
        ActivityUtils.startActivityIntent(getContext(), SureOrderActivity.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_search:
                ActivityUtils.startActivityIntent(getContext(), SearchFragmentActivity.class);
                break;
        }
    }

    @Override
    public Context context() {
        return getContext();
    }

    @Override
    public void showMsg(String msg) {

    }
}
