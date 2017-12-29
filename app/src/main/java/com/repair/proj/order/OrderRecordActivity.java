package com.repair.proj.order;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.repair.proj.R;
import com.repair.proj.base.BaseActivity;
import com.repair.proj.databinding.ActivityOrderRecordBinding;
import com.repair.proj.order.adapter.OrderRecordAdapter;
import com.repair.proj.order.contract.OrderRecordContract;
import com.repair.proj.order.presenter.OrderRecordPresenter;
import com.repair.proj.utils.Res;

import java.util.ArrayList;

/**
 * 创建人 LEO
 * 创建时间 2017/11/24 15:01
 */

public class OrderRecordActivity extends BaseActivity<OrderRecordPresenter,ActivityOrderRecordBinding> implements OrderRecordContract.View {
    private ArrayList<Object> orderRecords = new ArrayList<>();
    private OrderRecordAdapter adapter;

    @Override
    public int setContentView() {
        return R.layout.activity_order_record ;
    }

    @Override
    public void initView() {
        viewBinding.layoutTitle.setRightDrawable(Res.getDrawableRes(R.drawable.more,context));
        viewBinding.crvList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(0, 0, 0, 20);
            }
        });
        adapter = new OrderRecordAdapter(context,orderRecords) ;
        viewBinding.crvList.setAdapter(adapter);
    }

    @Override
    public void initData() {
        viewBinding.layoutTitle.setTitle("订单记录");
    }

    @Override
    public void initListener() {
        viewBinding.layoutTitle.setClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_back:
                finish();
                break ;
            default:
                break ;
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
