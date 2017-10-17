package com.repair.proj.utils;


import com.bumptech.glide.request.RequestOptions;
import com.repair.proj.R;

/**
 * Created by jniu on 2017/7/21.
 */

public class GlideUtils {
    public static RequestOptions defaultImg(){
        return new RequestOptions().placeholder(R.color.app_base_color).error(R.color.app_base_color);
    }
}
