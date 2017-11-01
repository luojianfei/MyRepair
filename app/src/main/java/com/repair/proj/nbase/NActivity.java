package com.repair.proj.nbase;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 说明：
 * Created by code_nil on 2017/10/27.
 */

public abstract class NActivity<T extends NPresenter, V extends ViewDataBinding> extends AppCompatActivity {
    protected T presenter;
    protected V binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeSetContentView();
        binding=DataBindingUtil.setContentView(this,getContentId());
        try {
            presenter = GenericHelper.newPresenter(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        onInit();
        onListener();
        if (presenter != null) {
            presenter.start();
        }
    }

    /**
     * 需要在SetContentView之前做的操作
     */
    protected void beforeSetContentView() {
    }

    /**
     * 在这里面进行初始化
     */
    protected void onInit() {
    }

    /**
     * 这里面写监听事件
     */
    protected void onListener() {
    }

    /**
     * 获取布局的id
     *
     * @return
     */
    protected abstract int getContentId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.end();
        }
    }
}
