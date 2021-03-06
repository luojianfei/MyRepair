package com.repair.proj.workerMain.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.repair.proj.R;
import com.repair.proj.databinding.ItemOrderListBinding;
import com.repair.proj.entity.OrderInfo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/11/1.
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyHolder> {

    public static final int TYPE_ORDER_CONDUCT = 0 ;//进行中
    public static final int TYPE_ORDER_COMPLETE = 1 ;//已完成
    public static final int TYPE_ORDER_CANCEL = 2 ;//已取消
    private CallBack callBack;
    private Context context;
    private ArrayList<OrderInfo> orderInfos;
    private RecyclerView recyclerView;
    private int orderType ;

    @IntDef({TYPE_ORDER_CONDUCT,TYPE_ORDER_COMPLETE,TYPE_ORDER_CANCEL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface OrderType{}

    public interface CallBack {
        void callBack(int position);
    }

    public OrderListAdapter(Context context,@OrderType int type, ArrayList<OrderInfo> orderInfos, CallBack callBack) {
        this.orderType = type;
        this.context = context;
        this.callBack = callBack;
        this.orderInfos = orderInfos;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_order_list, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemOrderListBinding binding;
        private View view;

        public MyHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            binding = DataBindingUtil.bind(itemView);
            binding.setType(orderType);
            binding.setClickListener(this);
        }

        private int getRealPosition() {
            return recyclerView.getChildLayoutPosition(view);
        }

        public void setData(int position) {

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_sure:
                    if (callBack != null) {
                        callBack.callBack(getRealPosition());
                    }
                    break;
            }
        }
    }

}
