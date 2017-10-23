package com.repair.proj.maindetail

import android.graphics.Typeface
import android.support.v4.graphics.TypefaceCompat
import android.support.v4.view.ViewPager
import android.text.InputType
import android.view.View
import android.widget.ImageView
import com.repair.proj.R
import com.repair.proj.maindetail.adapter.RepairTypeAdapter
import com.repair.proj.maindetail.contract.MainDetailContract
import com.repair.proj.maindetail.presenter.MainDetailPresenter
import io.xujiaji.xmvp.view.base.XBaseActivity
import kotlinx.android.synthetic.main.activity_main_detail.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.collections.forEachWithIndex
import org.jetbrains.anko.error
import org.jetbrains.anko.find

/**
 * 说明：
 * Created by code_nil on 2017/10/23.
 */

class MainDetailActivity : XBaseActivity<MainDetailPresenter>(), MainDetailContract.View, AnkoLogger {
    var tabItems = arrayOf("水电", "漆工", "木工", "泥工", "疏通")

    override fun beforeSetContentView() {
        super.beforeSetContentView()
    }

    override fun onInit() {
        super.onInit()
        //初始化加载项 tabTypes，layoutList需要从网络上获取
        var pagerViewList = arrayListOf<View>()
        var layoutList = arrayOf(R.drawable.md_sd, R.drawable.md_qg, R.drawable.md_mg, R.drawable.md_ng, R.drawable.md_st)
        tabItems.forEachWithIndex { id, data ->
            md_tab.addTab(md_tab.newTab().setText(data).setTag(id))
            var view = layoutInflater.inflate(R.layout.activity_main_detail_image, null)
            view.tag = data
            view.find<ImageView>(R.id.amdi_image).setImageResource(layoutList[id])
            pagerViewList.add(view)
        }

        var adapter = RepairTypeAdapter(pagerViewList)
        md_pj_vp.adapter = adapter
        md_tab.setupWithViewPager(md_pj_vp)
        md_tab.setTabsFromPagerAdapter(adapter)
        //设置字体
        md_title_select_project.typeface = Typeface.DEFAULT_BOLD
//        md_location_content.inputType = InputType.TYPE_NULL
    }

    override fun onListener() {
        super.onListener()
        //viewpager监听事件
        md_pj_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                //隐藏按钮
                when {
                    md_pj_vp.currentItem == 0 -> md_pj_arrow_l.visibility = View.GONE
                    md_pj_vp.currentItem == tabItems.size - 1 -> md_pj_arrow_r.visibility = View.GONE
                    else -> {
                        md_pj_arrow_l.visibility = View.VISIBLE
                        md_pj_arrow_r.visibility = View.VISIBLE
                    }
                }
            }

            override fun onPageSelected(position: Int) {}
        })
        //左箭头点击事件
        md_pj_arrow_l.setOnClickListener {
            md_pj_vp.setCurrentItem(md_pj_vp.currentItem - 1, true)
        }
        //右箭头点击事件
        md_pj_arrow_r.setOnClickListener {
            md_pj_vp.setCurrentItem(md_pj_vp.currentItem + 1, true)
        }
        //定位控件点击事件
        md_location_content.setOnClickListener{
            error { "猪头四" }
        }
    }


    override fun getContentId(): Int {
        return R.layout.activity_main_detail
    }

}
