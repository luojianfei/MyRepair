package com.repair.proj.maindetail

import android.content.Intent
import android.databinding.ObservableInt
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.support.v4.view.ViewPager
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import com.repair.proj.BR
import com.repair.proj.R
import com.repair.proj.base.Common
import com.repair.proj.databinding.ActivityMainDetailBinding
import com.repair.proj.maindetail.adapter.RepairTypeAdapter
import com.repair.proj.maindetail.contract.MainDetailContract
import com.repair.proj.maindetail.presenter.MainDetailPresenter
import com.repair.proj.nbase.NActivity
import kotlinx.android.synthetic.main.activity_main_detail.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.collections.forEachWithIndex
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivityForResult

/**
 * 说明：databinding只用于xml数据的绑定
 * Created by code_nil on 2017/10/23.
 */

class MainDetailActivity : NActivity<MainDetailPresenter, ActivityMainDetailBinding>(), MainDetailContract.View, AnkoLogger {

    private val tabItems = arrayOf("水电", "漆工", "木工", "泥工", "疏通")//tablayout项目
    private val layoutList = arrayOf(R.drawable.md_sd, R.drawable.md_qg, R.drawable.md_mg, R.drawable.md_ng, R.drawable.md_st)//viewpager图标
    private var pagerViewList = arrayListOf<View>()
    override fun onInit() {
        super.onInit()
        //初始化项目选择
        presenter.initCustomOptionPicker(md_pj_select_detail_picker, this)
        //默认不显示项目选择布局
        binding.pickerShow = false
        //默认数量为1
        //初始化加载项 tabTypes，layoutList需要从网络上获取
        md_tb_name.typeface = Typeface.DEFAULT_BOLD
        //初始化viewpager
        pagerViewList.clear()
        tabItems.forEachWithIndex { id, data ->
            //            md_tab.addTab(md_tab.newTab().setText(data).setTag(id))
            var view = layoutInflater.inflate(R.layout.activity_main_detail_image, null)
            view.tag = data
            view.find<ImageView>(R.id.amdi_image).setImageResource(layoutList[id])
            pagerViewList.add(view)
        }

        var adapter = RepairTypeAdapter(pagerViewList)
        md_pj_vp.adapter = adapter
        md_tab.setupWithViewPager(binding.mdPjVp)
        md_tab.setTabsFromPagerAdapter(adapter)
    }

    override fun onListener() {
        super.onListener()
        //viewpager监听事件
        md_pj_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                //隐藏按钮
                when {
                    md_pj_vp.currentItem == 0 -> binding.arrowLShow = false
                    md_pj_vp.currentItem == tabItems.size - 1 -> binding.arrowRShow = false
                    else -> {
                        binding.arrowLShow = true
                        binding.arrowRShow = true
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

        md_pj_select_ll.setOnClickListener {
            binding.pickerShow = !(binding.pickerShow ?: false)
            presenter.showOrDismissDetailPicker(binding.pickerShow ?: false)
        }

        md_num_reduce.setOnClickListener {
            var num = binding.num ?: 1
            num--
            if (num < 1) num = 1
            binding.num = num
        }

        md_num_add.setOnClickListener {
            var num = binding.num ?: 0
            num++
            if (num < 1) num = 1
            binding.num = num
        }


        md_scrollView.setOnTouchListener(object :View.OnTouchListener{
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                if(p0==md_pic) return false
                return true
            }

        })
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
