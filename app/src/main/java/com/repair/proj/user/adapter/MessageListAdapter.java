package com.repair.proj.user.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.repair.proj.R;
import com.repair.proj.databinding.ItemMessageListBinding;

import java.util.ArrayList;

/**
 * 创建人 LEO
 * 创建时间 2017/11/20 14:38
 */

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MyHolder> {
    private Context context ;
    private ArrayList<Object> objects ;

    public MessageListAdapter(Context context, ArrayList<Object> objects) {
        this.context = context;
        this.objects = objects;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_message_list,null) ;
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

        private View view ;
        private ItemMessageListBinding binding ;
        public MyHolder(View itemView) {
            super(itemView);
            this.view = itemView ;
            binding = DataBindingUtil.bind(view) ;
        }
    }
}
