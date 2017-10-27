package com.repair.proj.nbase;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 说明：
 * Created by code_nil on 2017/10/27.
 */

public abstract class N4Fragment<T extends NPresenter, B extends ViewDataBinding> extends Fragment {
    protected T presenter;
    protected B binding;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        try {
            presenter = GenericHelper.newPresenter(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        rootView = binding.getRoot();
        onInit();
        onListener();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (presenter != null) {
            presenter.start();
        }
    }

    /**
     * 添加监听
     */
    protected void onListener() {

    }

    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected void onInit() {
    }

    public View getRootView() {
        return this.rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.end();
        }
    }
}
