package com.repair.proj.workerMain;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.Gravity;
import android.view.View;
import android.widget.TabHost;

import com.repair.proj.R;
import com.repair.proj.base.BaseFragmentActivity;
import com.repair.proj.databinding.ActivityWorkerMainBinding;
import com.repair.proj.databinding.LayoutWorkerMainBottomItemBinding;
import com.repair.proj.utils.Res;

/**
 * Created by HX·罗 on 2017/10/31.
 */

public class WorkMainActivity extends BaseFragmentActivity<ActivityWorkerMainBinding>{

    private String tabNames[] = {"订单管理","材料管理"} ;//tab名字
    private Class fragmentArray[] = {OrderManageFragment.class,MaterialManageFragment.class} ;
    private Drawable[] tabDrawable;
    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_worker_main ;
    }
    @Override
    public void initView() {
        mTabHost = findView(android.R.id.tabhost);

        tabDrawable = new Drawable[]{Res.getDrawableRes(R.drawable.icon_material,context),
                Res.getDrawableRes(R.drawable.icon_material,context)};
        mTabHost.setup(context,getSupportFragmentManager(),R.id.fl_content);
        for (int i = 0 ; i < fragmentArray.length ; i++){
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tabNames[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            //设置Tab按钮的背景
            mTabHost.getTabWidget().setGravity(Gravity.CENTER_VERTICAL);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        viewBinding.setClickListener(this);
        viewBinding.layoutDrawer.setClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_right:
                viewBinding.mdDrawerLeft.openDrawer(Gravity.RIGHT);
                break;
        }
    }
    /**
     * 获取tab item
     * @param index
     * @return
     */
    private View getTabItemView(int index){
        View view = inflater.inflate(R.layout.layout_worker_main_bottom_item, null);
        LayoutWorkerMainBottomItemBinding binding = DataBindingUtil.bind(view) ;
        binding.setTabName(tabNames[index]);
        binding.setDrawable(tabDrawable[index]);
        return view;
    }

    @Override
    public void onBackPressed() {
        if(viewBinding.mdDrawerLeft.isDrawerOpen(Gravity.RIGHT)){
            viewBinding.mdDrawerLeft.closeDrawers();
        }else{
            super.onBackPressed();
        }
    }
}
