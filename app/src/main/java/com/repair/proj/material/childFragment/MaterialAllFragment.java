package com.repair.proj.material.childFragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.repair.proj.R;
import com.repair.proj.base.BaseFragment;
import com.repair.proj.databinding.FragmentCancelBinding;
import com.repair.proj.databinding.FragmentMaterialAllBinding;
import com.repair.proj.entity.GoodInfo;
import com.repair.proj.entity.OrderInfo;
import com.repair.proj.material.adapter.MaterialAllListAdapter;
import com.repair.proj.material.contract.MaterialAllContract;
import com.repair.proj.material.presenter.MaterialAllPresenter;
import com.repair.proj.workerMain.adapter.OrderListAdapter;
import com.repair.proj.workerMain.contract.CancelContract;
import com.repair.proj.workerMain.presenter.CancelPresenter;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/11/1.
 */

public class MaterialAllFragment extends BaseFragment<MaterialAllPresenter,FragmentMaterialAllBinding> implements MaterialAllContract.View {
    private MaterialAllListAdapter listAdapter;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_material_all;
    }

    @Override
    public void initView() {
        listAdapter = new MaterialAllListAdapter(getContext(),new ArrayList<GoodInfo>());
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
