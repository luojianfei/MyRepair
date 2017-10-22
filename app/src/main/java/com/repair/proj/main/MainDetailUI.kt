package com.repair.proj.main

import android.graphics.Color
import android.support.design.widget.TabLayout
import android.view.Gravity
import android.view.View
import com.repair.proj.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.support.v4.viewPager

/**
 * 说明：
 * Created by code_nil on 2017/10/20.
 */
class MainDetailUI : AnkoComponent<MainDetailActivity> {
    companion object {
        const val MDUI_tb = 1
        const val MDUI_tb_log = 2
        const val MDUI_tb_name = 3
        const val MDUI_tb_contacts = 4
        const val MDUI_tb_tab = 5
        const val MDUI_tb_frameLayout = 6
        const val MDUI_tb_frameLayout_viewpager = 7
        const val MDUI_tb_frameLayout_left_arraw = 8
        const val MDUI_tb_frameLayout_right_arraw = 9
        const val MDUI_divier1 = 10
        const val MDUI_divier2 = 11
        const val MDUI_ll_project_select = 12
        const val MDUI_ll_tv_image = 13
        const val MDUI_divier3 = 14
    }

    override fun createView(ui: AnkoContext<MainDetailActivity>): View = with(ui) {
        relativeLayout {
            relativeLayout {
                id = MDUI_tb
                imageView(R.drawable.repair) {
                    id = MDUI_tb_log
                }.lparams {
                    leftMargin = dip(8)
                    centerVertically()
                }

                textView(resources.getString(R.string.md_repair)) {
                    id = MDUI_tb_name
                    gravity = Gravity.CENTER
                }.lparams {
                    rightOf(MDUI_tb_log)
                    leftMargin = dip(8)
                    centerVertically()
                }

                imageView(R.drawable.icon_contacts) {
                    id = MDUI_tb_contacts
                }.lparams {
                    alignParentRight()
                    rightMargin = dip(8)
                    centerVertically()
                }

            }.lparams(width = matchParent, height = dip(px2dip(102))) {
                alignParentTop()
            }

            tabLayout {
                id = MDUI_tb_tab
                tabMode = TabLayout.MODE_SCROLLABLE
                setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#ff9800"))
                setSelectedTabIndicatorColor(Color.parseColor("#ff9800"))

            }.lparams(width = matchParent, height = dip(px2dip(85))) {
                below(MDUI_tb)
            }

            textView {
                id = MDUI_divier1
                backgroundColor = Color.parseColor("#bebebe")
            }.lparams(width = matchParent, height = dip(1)) { below(MDUI_tb_tab) }

            relativeLayout {
                id = MDUI_tb_frameLayout
                imageView(R.drawable.ic_left_arrow) { id = MDUI_tb_frameLayout_left_arraw }
                        .lparams(width = dip(18), height = matchParent) {
                            centerVertically()
                            leftMargin = dip(16)
                            alignParentLeft()
                        }

                imageView(R.drawable.ic_right_arrow) { id = MDUI_tb_frameLayout_right_arraw }
                        .lparams(width = dip(18), height = matchParent) {
                            centerVertically()
                            rightMargin = dip(16)
                            alignParentRight()
                        }

                viewPager {
                    id = MDUI_tb_frameLayout_viewpager
                }
                        .lparams(width = matchParent, height = matchParent) {
                            leftOf(MDUI_tb_frameLayout_right_arraw)
                            rightOf(MDUI_tb_frameLayout_left_arraw)
                            leftMargin = dip(16)
                            rightMargin = dip(16)
                        }

            }.lparams(width = matchParent, height = dip(px2dip(300))) { below(MDUI_divier1) }

            textView {
                id = MDUI_divier2
                backgroundColor = Color.parseColor("#bebebe")
            }.lparams(width = matchParent, height = dip(1)) { below(MDUI_tb_frameLayout) }

            linearLayout {
                id = MDUI_ll_project_select
                textView("选择项目") {
                    gravity = Gravity.START or Gravity.CENTER
                }.lparams(width = 0, height = matchParent, weight = 1f)

                textView("展开") {
                    id = MDUI_ll_tv_image
                    textColor = Color.parseColor("#ff9800")
                    gravity = Gravity.END or Gravity.CENTER
                }.lparams(width = 0, height = matchParent, weight = 1f)

            }.lparams(width = matchParent, height = dip(px2dip(92))) {
                below(MDUI_divier2)
                rightMargin = dip(8)
                leftMargin = dip(8)
            }

            textView {
                id = MDUI_divier3
                backgroundColor = Color.parseColor("#bebebe")
            }.lparams(width = matchParent, height = dip(1)) { below(MDUI_ll_project_select) }

            relativeLayout {
                backgroundColor = Color.parseColor("#f0f0f0")
                textView("未知") {  }
            }.lparams(width = matchParent, height = dip(px2dip(480))) { below(MDUI_divier3) }
        }
    }
}