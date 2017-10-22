package com.repair.proj.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.repair.proj.R
import org.jetbrains.anko.*
import org.jetbrains.anko.collections.forEachWithIndex

/**
 * 说明：
 * Created by code_nil on 2017/10/20.
 */
class MainDetailActivity : AppCompatActivity(), AnkoLogger {
    private lateinit var tabLayout: TabLayout
    private lateinit var leftArrow: ImageView
    private lateinit var rightArrow: ImageView
    private lateinit var pager: ViewPager
    private lateinit var slectType: TextView
    private lateinit var locationC1: TextView
    private lateinit var locationC2: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainDetailUI().setContentView(this)
        initView()
        initLogic()
    }


    private fun initView() {
        //init TabLayout
        tabLayout = find(MainDetailUI.MDUI_tb_tab)
        leftArrow = find(MainDetailUI.MDUI_tb_frameLayout_left_arraw)
        rightArrow = find(MainDetailUI.MDUI_tb_frameLayout_right_arraw)
        pager = find(MainDetailUI.MDUI_tb_frameLayout_viewpager)
        slectType = find(MainDetailUI.MDUI_ll_tv_image)
        locationC1 = find(MainDetailUI.MDUI_location_auto)
        locationC2 = find(MainDetailUI.MDUI_location_input)
    }

    private fun initLogic() {

        //初始化加载项 tabTypes，layoutList需要从网络上获取
        var tabItems = arrayOf("水电", "漆工", "木工", "泥工", "疏通")
        var pagerViewList = arrayListOf<View>()
        var layoutList = arrayOf(R.drawable.md_sd, R.drawable.md_qg, R.drawable.md_mg, R.drawable.md_ng, R.drawable.md_st)
        tabItems.forEachWithIndex { id, data ->
            tabLayout.addTab(tabLayout.newTab().setText(data).setTag(id))
            var view = layoutInflater.inflate(R.layout.activity_main_detail_image, null)
            view.tag = data
            view.find<ImageView>(R.id.amdi_image).setImageResource(layoutList[id])
            pagerViewList.add(view)
        }
        //init viewpager
        var adapter = RepairTypeAdapter(pagerViewList)
        pager.adapter = adapter
        tabLayout.setupWithViewPager(pager)
        tabLayout.setTabsFromPagerAdapter(adapter)
        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                //隐藏按钮
                when {
                    pager.currentItem == 0 -> leftArrow.visibility = View.GONE
                    pager.currentItem == tabItems.size - 1 -> rightArrow.visibility = View.GONE
                    else -> {
                        leftArrow.visibility = View.VISIBLE
                        rightArrow.visibility = View.VISIBLE
                    }
                }
            }

            override fun onPageSelected(position: Int) {}
        })

        //初始化左右箭头的点击事件
        leftArrow.setOnClickListener {
            pager.setCurrentItem(pager.currentItem - 1, true)
        }
        rightArrow.setOnClickListener {
            pager.setCurrentItem(pager.currentItem + 1, true)
        }

        //selecttype
        var drawable = resources.getDrawable(R.drawable.arrow_down)
        drawable.setBounds(0, 0, 48, 48)
        slectType.setCompoundDrawables(null, null, drawable, null)

        //定位

        var drawableC1 = resources.getDrawable(R.drawable.location_c2)
        drawableC1.setBounds(0, 0, 48, 48)
        locationC1.setCompoundDrawables(drawableC1, null, null, null)
        locationC1.leftPadding = 48//在dsl中不起作用，因此在这里设置

        var drawableC2 = resources.getDrawable(R.drawable.location_c1)
        drawableC2.setBounds(0, 0, 48, 48)
        locationC2.setCompoundDrawables(drawableC2, null, null, null)
        locationC2.leftPadding = 48//在dsl中不起作用，因此在这里设置
    }
}