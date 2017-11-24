package com.repair.proj.order.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.repair.proj.R;
import com.repair.proj.databinding.ItemOrderRecordBinding;

import java.util.ArrayList;

/**
 * 创建人 LEO
 * 创建时间 2017/11/24 15:05
 */

public class OrderRecordAdapter extends RecyclerView.Adapter<OrderRecordAdapter.MyHolder>{
    private Context context ;
    private ArrayList<Object> orderRecords ;

    public OrderRecordAdapter(Context context, ArrayList<Object> orderRecords) {
        this.context = context;
        this.orderRecords = orderRecords;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_order_record,null) ;
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
        private ItemOrderRecordBinding binding ;
        public MyHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView) ;
        }
    }
}
