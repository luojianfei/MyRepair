package com.repair.proj.material.childFragment;

import android.content.Context;
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
        viewBinding.tv1.setItem(new String[]{"手持花洒","淋浴器","淋雨龙头","升降花洒"});
        viewBinding.tv2.setItem(new String[]{"手持花洒1","淋浴器1"});
        viewBinding.tv3.setItem(new String[]{"手持花洒2","淋浴器2","淋雨龙头","升降花洒"});
        viewBinding.tv4.setItem(new String[]{"手持花洒3","淋浴器2"});
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
