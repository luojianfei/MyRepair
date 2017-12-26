package com.repair.proj.customView;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.repair.proj.R;
import com.repair.proj.databinding.LayoutSelectCountBinding;
import com.repair.proj.utils.KeyBoardUtils;
import com.repair.proj.utils.TextUtil;

/**
 * 创建人 LEO
 * 创建时间 2017/12/26 16:56
 */

public class SelectCountView extends LinearLayout implements View.OnClickListener{
    private Context context ;
    private LayoutSelectCountBinding binding;

    public SelectCountView(Context context) {
        this(context,null);
    }

    public SelectCountView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SelectCountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context ;
        initView();
        initListener();
    }

    public void initView(){
        View view = View.inflate(context, R.layout.layout_select_count,null) ;
        addView(view);
        binding = DataBindingUtil.bind(view);
        binding.setNum("0");
    }
    private void initListener() {
        binding.setClickListener(this);
        binding.etCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtil.isEmpty(s.toString())){
                    binding.setNum("0");
                    binding.etCount.clearFocus();
                    KeyBoardUtils.closeKeybord(binding.etCount,context);
                }
            }
        });
    }

    public int getCount(){
        int count = Integer.parseInt(binding.getNum()) ;
        return count ;
    }
    @Override
    public void onClick(View v) {
        int num;
        switch (v.getId()) {
            case R.id.btn_add:
                binding.etCount.clearFocus();
                KeyBoardUtils.closeKeybord(binding.etCount,context);
                num = Integer.parseInt(binding.getNum());
                binding.setNum(num + 1 + "");
                break;
            case R.id.btn_plus:
                binding.etCount.clearFocus();
                KeyBoardUtils.closeKeybord(binding.etCount,context);
                num = Integer.parseInt(binding.getNum());
                if (num > 0) {
                    binding.setNum(num - 1 + "");
                }
                break;
        }
    }
}
