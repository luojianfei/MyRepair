package com.repair.proj.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.repair.proj.R;
import com.repair.proj.databinding.LayoutMaterialTypeBinding;
import com.repair.proj.utils.TextUtil;

/**
 * 创建人 LEO
 * 创建时间 2017/12/27 16:41
 */

public class MaterialTypeView extends LinearLayout {
    private Context context;
    private LayoutMaterialTypeBinding binding;
    private String title;
    private String items[] = null;

    public MaterialTypeView(Context context) {
        this(context, null);
    }

    public MaterialTypeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaterialTypeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MaterialTypeView);
        title = ta.getString(R.styleable.MaterialTypeView_title);
        this.context = context;
        initView();
    }

    private void initView() {
        View view = View.inflate(context, R.layout.layout_material_type, null);
        addView(view);
        binding = DataBindingUtil.bind(view);
        if (!TextUtil.isEmpty(title))
            binding.setTitle(title);
    }

    private View createChildView(String firstStr, String secondStr) {
        TextView firstView = null;
        TextView secondView = null;
        if (!TextUtil.isEmpty(firstStr)) {
            firstView = getItemView(firstStr);
        }
        if (!TextUtil.isEmpty(secondStr)) {
            secondView = getItemView(secondStr);
        }
        if (firstView == null && secondView == null) {
            return null;
        }
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 100));
        linearLayout.setBackgroundColor(Color.WHITE);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.addView(getItemView(firstStr));
        linearLayout.addView(getVerticalDivider());
        linearLayout.addView(getItemView(secondStr));
        return linearLayout;
    }

    private TextView getItemView(String content) {
        TextView tv = new TextView(context);
        if (TextUtil.isEmpty(content)) {
            tv.setVisibility(View.INVISIBLE);
            content = "";
        }
        tv.setLayoutParams(new LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f));
        tv.setBackgroundColor(Color.parseColor("#EBEBEB"));
        tv.setTextSize(14);
        tv.setTextColor(Color.parseColor("#000000"));
        tv.setGravity(Gravity.CENTER);
        tv.setText(content);
        return tv;
    }

    /**
     * 设置item内容
     *
     * @param strings
     */
    public void setItem(String[] strings) {
        if (strings != null) {
            this.items = strings;
            binding.llContent.removeAllViews();
            updateItem();
        }
    }

    /**
     * 更新数据
     */
    private void updateItem() {
        for (int i = 0; i < items.length; i += 2) {
            String firstStr = items[i];
            String secondStr = null;
            if (i + 1 < items.length) {
                secondStr = items[i + 1];
            }
            binding.llContent.addView(createChildView(firstStr, secondStr));
            if (i + 2 < items.length) {
                binding.llContent.addView(getHorizontalDivider());
            }
        }
    }

    /**
     * 水平分割线
     *
     * @return
     */
    private View getHorizontalDivider() {
        View view = new View(context);
        view.setBackgroundColor(Color.WHITE);
        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 8));
        return view;
    }

    /**
     * 垂直分割线
     *
     * @return
     */
    private View getVerticalDivider() {
        View view = new View(context);
        view.setBackgroundColor(Color.WHITE);
        view.setLayoutParams(new LayoutParams(8, LayoutParams.MATCH_PARENT));
        return view;
    }
}
