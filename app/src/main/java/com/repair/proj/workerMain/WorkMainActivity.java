package com.repair.proj.workerMain;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.TabHost;

import com.repair.proj.R;
import com.repair.proj.base.BaseFragmentActivity;
import com.repair.proj.base.BasePresenter;
import com.repair.proj.databinding.ActivityWorkerMainBinding;
import com.repair.proj.databinding.LayoutWorkerMainBottomItemBinding;
import com.repair.proj.order.OrderRecordActivity;
import com.repair.proj.user.InviteRewardActivity;
import com.repair.proj.user.MessageActivity;
import com.repair.proj.user.MoreSettingActivity;
import com.repair.proj.user.MyCouponActivity;
import com.repair.proj.user.MyWorkerActivity;
import com.repair.proj.user.ServiceCenterActivity;
import com.repair.proj.utils.Res;
import com.repair.proj.utils.TextUtil;

/**
 * Created by HX·罗 on 2017/10/31.
 */

public class WorkMainActivity extends BaseFragmentActivity {

    private String tabNames[] = {"订单管理", "材料管理"};//tab名字
    private Class fragmentArray[] = {OrderManageFragment.class, MaterialManageFragment.class};
    private Drawable[] tabDrawable;
    private FragmentTabHost mTabHost;
    private ActivityWorkerMainBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_worker_main;
    }
    @Override
    public void initView() {
        viewBinding = DataBindingUtil.bind(parentView);
        mTabHost = findViewById(android.R.id.tabhost);
        tabDrawable = new Drawable[]{Res.getDrawableRes(R.drawable.icon_material, context),
                Res.getDrawableRes(R.drawable.icon_material, context)};
        mTabHost.setup(context, getSupportFragmentManager(), R.id.fl_content);
        for (int i = 0; i < fragmentArray.length; i++) {
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
        switch (view.getId()) {
            case R.id.iv_right:
                viewBinding.mdDrawerLeft.openDrawer(Gravity.RIGHT);
                break;
            case R.id.md_side_user_evaluate://用户评价
//                startActivity(OrderRecordActivity.class);
                break;
            case R.id.md_side_financial_data://财务数据
//                startActivity(MyWorkerActivity.class);
                break;
            case R.id.md_side_stock://库存查看
//                startActivity(MyCouponActivity.class);
                break;
            case R.id.md_side_mail://收件箱
                startActivity(MessageActivity.class);
                break;
            case R.id.md_side_service://客服中心
                startActivity(ServiceCenterActivity.class);
                break;
            case R.id.md_side_material_purchase://材料进货
//                startActivity(InviteRewardActivity.class);
                break;
            case R.id.md_side_setting://更多设置
                startActivity(MoreSettingActivity.class);
                break;
        }
    }

    /**
     * 获取tab item
     *
     * @param index
     * @return
     */
    private View getTabItemView(int index) {
        View view = inflater.inflate(R.layout.layout_worker_main_bottom_item, null);
        LayoutWorkerMainBottomItemBinding binding = DataBindingUtil.bind(view);
        binding.setTabName(tabNames[index]);
        binding.setDrawable(tabDrawable[index]);
        return view;
    }

    @Override
    public void onBackPressed() {
        if (viewBinding.mdDrawerLeft.isDrawerOpen(Gravity.RIGHT)) {
            viewBinding.mdDrawerLeft.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }
}
