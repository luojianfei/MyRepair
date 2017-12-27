package com.repair.proj.material.childFragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.repair.proj.R;
import com.repair.proj.base.BaseFragment;
import com.repair.proj.databinding.FragmentCompleteBinding;
import com.repair.proj.databinding.FragmentMaterialTypeBinding;
import com.repair.proj.entity.OrderInfo;
import com.repair.proj.material.contract.MaterialTypeContract;
import com.repair.proj.material.presenter.MaterialTypePresenter;
import com.repair.proj.order.SureOrderActivity;
import com.repair.proj.utils.ActivityUtils;
import com.repair.proj.workerMain.adapter.OrderListAdapter;
import com.repair.proj.workerMain.contract.CompleteContract;
import com.repair.proj.workerMain.presenter.CompletePresenter;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/11/1.
 */

public class MaterialTypeFragment extends BaseFragment<MaterialTypePresenter,FragmentMaterialTypeBinding> implements MaterialTypeContract.View ,OrderListAdapter.CallBack{
    private OrderListAdapter listAdapter;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_material_type ;
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

    @Override
    public void callBack(int position) {
        ActivityUtils.startActivityIntent(getContext(), SureOrderActivity.class);
    }

    @Override
    public void onClick(View v) {

    }
}
