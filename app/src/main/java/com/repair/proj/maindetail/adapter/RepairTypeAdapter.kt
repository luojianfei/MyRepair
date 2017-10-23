package com.repair.proj.maindetail.adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup


/**
 * 说明：
 * Created by code_nil on 2017/10/20.
 */
class RepairTypeAdapter(list: List<View>) : PagerAdapter() {
    var list = list
    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(list[position])//添加页卡
        return list[position]
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(list[position])//删除页卡
    }

    override fun getPageTitle(position: Int): CharSequence {
        return list[position].tag.toString()//页卡标题.toString()
    }
}