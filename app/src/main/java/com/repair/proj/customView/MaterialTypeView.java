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
        binding.llContent.addView(createChildView());
        binding.llContent.addView(getHorizontalDivider());
        binding.llContent.addView(createChildView());
    }

    private View createChildView() {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 100));
        linearLayout.setBackgroundColor(Color.WHITE);
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.addView(getItemView("淋浴器"));
        linearLayout.addView(getVerticalDivider());
        linearLayout.addView(getItemView("升降花洒"));
        return linearLayout;
    }

    private TextView getItemView(String content) {
        TextView tv = new TextView(context);
        tv.setLayoutParams(new LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f));
        tv.setBackgroundColor(Color.parseColor("#EBEBEB"));
        tv.setTextSize(14);
        tv.setTextColor(Color.parseColor("#000000"));
        tv.setGravity(Gravity.CENTER);
        tv.setText(content);
        return tv;
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
