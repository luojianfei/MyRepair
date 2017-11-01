package com.repair.proj.workerMain.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.repair.proj.R;
import com.repair.proj.databinding.ItemOrderListBinding;
import com.repair.proj.entity.OrderInfo;

import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/11/1.
 */

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyHolder> {

    private Context context ;
    private ArrayList<OrderInfo> orderInfos ;
    public OrderListAdapter(Context context,ArrayList<OrderInfo> orderInfos){
        this.context = context ;
        this.orderInfos = orderInfos ;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_order_list,null) ;
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

    class MyHolder extends RecyclerView.ViewHolder{

        private ItemOrderListBinding binding ;
        public MyHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView) ;
        }
    }

}
