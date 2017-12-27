package com.repair.proj.material.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.repair.proj.R;
import com.repair.proj.databinding.ItemMaterialAllListBinding;
import com.repair.proj.entity.GoodInfo;

import java.util.ArrayList;

/**
 * 创建人 LEO
 * 创建时间 2017/12/27 14:22
 */

public class MaterialAllListAdapter extends RecyclerView.Adapter<MaterialAllListAdapter.MyHolder>{
    private Context context ;
    private ArrayList<GoodInfo> goodInfos ;

    public MaterialAllListAdapter(Context context, ArrayList<GoodInfo> goodInfos) {
        this.context = context;
        this.goodInfos = goodInfos;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_material_all_list,null) ;
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 9;
    }

    class MyHolder extends RecyclerView.ViewHolder{

        private final ItemMaterialAllListBinding binding;

        public MyHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

}
