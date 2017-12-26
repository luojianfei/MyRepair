package com.repair.proj.workerMain.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;

import com.repair.proj.R;
import com.repair.proj.databinding.ItemMaterialListBinding;
import com.repair.proj.entity.MaterialInfo;
import com.repair.proj.utils.KeyBoardUtils;
import com.repair.proj.utils.ScreenUtils;
import com.repair.proj.utils.TextUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

/**
 * Created by HX·罗 on 2017/11/1.
 */

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MyHolder> {

    public static final int TYPE_MATERIAL_PURCHAS = 0;//采购中
    public static final int TYPE_MATERIAL_HAVE = 1;//已有材料
    public static final int TYPE_MATERIAL_SALE = 2;//售出材料
    private CallBack callBack;
    private Context context;
    private ArrayList<MaterialInfo> materialInfos;
    private RecyclerView recyclerView;
    private int materialType;

    @IntDef({TYPE_MATERIAL_PURCHAS, TYPE_MATERIAL_HAVE, TYPE_MATERIAL_SALE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MaterialType {
    }

    public interface CallBack {
        void callBack(int position);
    }

    public MaterialAdapter(Context context, @MaterialType int type, ArrayList<MaterialInfo> materialInfos, CallBack callBack) {
        this.materialType = type;
        this.context = context;
        this.callBack = callBack;
        this.materialInfos = materialInfos;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_material_list, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemMaterialListBinding binding;
        private View view;

        public MyHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            binding = DataBindingUtil.bind(itemView);
            binding.setType(materialType);
            initListener();
        }

        private void initListener() {
            binding.setClickListener(this);
        }

        private int getRealPosition() {
            return recyclerView.getChildLayoutPosition(view);
        }

        public void setData(int position) {
            binding.setMatrialInfo(new MaterialInfo());
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
