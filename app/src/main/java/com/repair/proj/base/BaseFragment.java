package com.repair.proj.base;


import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.repair.proj.nbase.GenericHelper;
import com.repair.proj.nbase.NPresenter;

/**
 * 作者: Administrator on 2016-11-01 14:31.
 */

public abstract class BaseFragment<T extends NPresenter,G extends ViewDataBinding> extends Fragment implements View.OnClickListener{

    /**
     * 贴附的activity
     */
    protected Activity mActivity;

    /**
     * 根view
     */
    protected View mRootView;

    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;
    public G viewBinding;
    public T presenter;
    /**
     * 是否加载完成
     * 当执行完oncreatview,View的初始化方法后方法后即为true
     */
    protected boolean mIsPrepare;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(setLayoutResouceId(), container, false);
        try {
            viewBinding = DataBindingUtil.bind(mRootView);
            presenter = GenericHelper.newPresenter(this) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData(getArguments());
        mIsPrepare = true;
        onLazyLoad();
        initListener();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        this.mIsVisible = isVisibleToUser;

        if (isVisibleToUser) {
            onVisibleToUser();
        }
    }

    /**
     * 用户可见时执行的操作
     */
    protected void onVisibleToUser() {
        if (mIsPrepare && mIsVisible) {
            onLazyLoad();
        }
    }

    /**
     * 懒加载，仅当用户可见切view初始化结束后才会执行
     */
    protected void onLazyLoad() {

    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T findViewById(int id) {
        if (mRootView == null) {
            return null;
        }

        return (T) mRootView.findViewById(id);
    }

    /**
     * 设置根布局资源id
     *
     * @return
     */
    protected abstract int setLayoutResouceId();


    /**
     * 初始化组件
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData(Bundle arguments);

    /**
     * 初始化监听
     */
    public abstract void initListener();
}