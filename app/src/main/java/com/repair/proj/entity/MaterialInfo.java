package com.repair.proj.entity;

import com.repair.proj.workerMain.adapter.MaterialAdapter;

/**
 * Created by HX·罗 on 2017/11/1.
 */

public class MaterialInfo {
    private int price = 120;
    private int count = 20;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getFormatPrice(int type) {
        switch (type) {
            case MaterialAdapter.TYPE_MATERIAL_PURCHAS:
                return String.format("单价￥%d",price);
            case MaterialAdapter.TYPE_MATERIAL_SALE:
                return String.format("共计￥%d",price);
            case MaterialAdapter.TYPE_MATERIAL_HAVE:
                return String.format("已付￥%d",price);
        }
        return "" ;
    }
}
