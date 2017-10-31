package com.repair.proj.maindetail

import android.content.Intent
import android.graphics.Typeface
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.ImageView
import com.android.databinding.library.baseAdapters.BR
import com.repair.proj.R
import com.repair.proj.base.Common
import com.repair.proj.databinding.ActivityMainBinding
import com.repair.proj.databinding.ActivityMainDetailBinding
import com.repair.proj.maindetail.adapter.RepairTypeAdapter
import com.repair.proj.maindetail.contract.MainDetailContract
import com.repair.proj.maindetail.presenter.MainDetailPresenter
import com.repair.proj.nbase.NActivity
import kotlinx.android.synthetic.main.activity_main_detail.*
import org.jetbrains.anko.*
import org.jetbrains.anko.collections.forEachWithIndex

/**
 * 说明：databinding只用于xml数据的绑定
 * Created by code_nil on 2017/10/23.
 */

class MainDetailActivity : NActivity<MainDetailPresenter, ActivityMainDetailBinding>(), MainDetailContract.View, AnkoLogger {
    var tabItems = arrayOf("水电", "漆工", "木工", "泥工", "疏通")

    override fun beforeSetContentView() {
        super.beforeSetContentView()
        //可以用于设置状态栏
    }

    override fun onInit() {
        super.onInit()
        //初始化加载项 tabTypes，layoutList需要从网络上获取
        var pagerViewList = arrayListOf<View>()
        var layoutList = arrayOf(R.drawable.md_sd, R.drawable.md_qg, R.drawable.md_mg, R.drawable.md_ng, R.drawable.md_st)
        tabItems.forEachWithIndex { id, data ->
            binding.mdTab.addTab(binding.mdTab.newTab().setText(data).setTag(id))
            var view = layoutInflater.inflate(R.layout.activity_main_detail_image, null)
            view.tag = data
            view.find<ImageView>(R.id.amdi_image).setImageResource(layoutList[id])
            pagerViewList.add(view)
        }

        var adapter = RepairTypeAdapter(pagerViewList)
        md_pj_vp.adapter = adapter
        md_tab.setupWithViewPager(binding.mdPjVp)
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
            binding.mdPjVp.setCurrentItem(binding.mdPjVp.currentItem - 1, true)
        }
        //右箭头点击事件
        md_pj_arrow_r.setOnClickListener {
            binding.mdPjVp.setCurrentItem(binding.mdPjVp.currentItem + 1, true)
        }
        //定位控件点击事件
        md_location_content.setOnClickListener {
            startActivityForResult<LocationActivity>(Common.LOCATION_REQUEST_CODE)
        }
    }

    override fun getContentId(): Int {
        return R.layout.activity_main_detail
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Common.LOCATION_REQUEST_CODE && resultCode == Common.LOCATION_RESULT_CODE) {
            binding.address = data?.getStringExtra("address") ?: ""
        }
    }
}
