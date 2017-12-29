package com.repair.proj.material;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.repair.proj.R;
import com.repair.proj.base.BaseFragmentActivity;
import com.repair.proj.databinding.ActivityFragmentSearchBinding;
import com.repair.proj.material.adapter.CustomSearchPagerAdapter;
import com.repair.proj.material.contract.SearchContract;
import com.repair.proj.material.presenter.SearchPresenter;

/**
 * 创建人 LEO
 * 创建时间 2017/12/27 11:07
 */

public class SearchFragmentActivity extends BaseFragmentActivity<SearchPresenter,ActivityFragmentSearchBinding> implements SearchContract.View{
    private CustomSearchPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_fragment_search;
    }

    @Override
    public void initView() {
        pagerAdapter = new CustomSearchPagerAdapter(getSupportFragmentManager());
        viewBinding.vpSearchMaterial.setAdapter(pagerAdapter);
        viewBinding.mdTab.setupWithViewPager(viewBinding.vpSearchMaterial);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        viewBinding.setClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public Context context() {
        return context;
    }

    @Override
    public void showMsg(String msg) {
        showShortToast(msg);
    }
}
