package com.repair.proj.main

import android.graphics.Color
import android.graphics.Typeface
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
        const val MDUI_divier4 = 15
        const val MDUI_rl_location = 16
        const val MDUI_location_auto = 17
        const val MDUI_location_input = 18
        const val MDUI_money = 19
        const val MDUI_order = 20
        const val MDUI_location_add = 21
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

            }.lparams(width = matchParent, height = dip(50)) {
                alignParentTop()
            }

            tabLayout {
                id = MDUI_tb_tab
                tabMode = TabLayout.MODE_SCROLLABLE
                setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#ff9800"))
                setSelectedTabIndicatorColor(Color.parseColor("#ff9800"))

            }.lparams(width = matchParent, height = dip(42)) {
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

            }.lparams(width = matchParent, height = dip(150)) { below(MDUI_divier1) }

            textView {
                id = MDUI_divier2
                backgroundColor = Color.parseColor("#bebebe")
            }.lparams(width = matchParent, height = dip(1)) { below(MDUI_tb_frameLayout) }

            linearLayout {
                id = MDUI_ll_project_select
                textView("选择项目") {
                    gravity = Gravity.START or Gravity.CENTER
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = 0, height = matchParent, weight = 1f)

                textView("展开") {
                    id = MDUI_ll_tv_image
                    textColor = Color.parseColor("#ff9800")
                    gravity = Gravity.END or Gravity.CENTER
                    compoundDrawablePadding=dip(8)
                }.lparams(width = 0, height = matchParent, weight = 1f)

            }.lparams(width = matchParent, height = dip(46)) {
                below(MDUI_divier2)
                rightMargin = dip(8)
                leftMargin = dip(8)
            }

            textView {
                id = MDUI_divier3
                backgroundColor = Color.parseColor("#bebebe")
            }.lparams(width = matchParent, height = dip(1)) { below(MDUI_ll_project_select) }

            verticalLayout {
                id = MDUI_rl_location
                backgroundColor = Color.parseColor("#f0f0f0")
                textView("未知") {
                    id = MDUI_location_auto
                    backgroundColor = Color.parseColor("#ffffff")
                    compoundDrawablePadding = dip(8)
                    gravity = Gravity.CENTER_VERTICAL
                }.lparams(width = matchParent, height = dip(56)) {
                    setMargins(dip(8), dip(8), dip(8), 0)
                }

                textView {
                    id = MDUI_divier4
                    backgroundColor = Color.parseColor("#bebebe")
                }.lparams(width = matchParent, height = dip(1)) {
                    setMargins(dip(8), 0, dip(8), 0)
                }

                linearLayout {
                    editText {
                        id = MDUI_location_input
                        backgroundColor = Color.parseColor("#ffffff")
                        hint = "在此输入目的地"
                        hintTextColor = Color.parseColor("#8f8f8f")
                        textColor = Color.parseColor("#111111")
                        textSize = 14f
                        compoundDrawablePadding = dip(8)
                    }.lparams(width = 0, height = dip(56), weight = 10f) {}

                    imageView(R.drawable.type_add) {
                        id=MDUI_location_add
                    }.lparams(width =  dip(16), height = dip(16), weight = 1f) {
                        backgroundColor=Color.parseColor("#ffffff")
                        gravity=Gravity.CENTER_VERTICAL
                    }
                }.lparams(width = matchParent, height = dip(56)) {
                    setMargins(dip(8), 0, dip(8), 0)
                    leftPadding = dip(8)
                }

            }.lparams(width = matchParent, height = dip(200)) {
                below(MDUI_divier3)
            }

            linearLayout {
                id = MDUI_money
                textView("人工优惠价￥") {
                    textSize = sp(px2sp(14)).toFloat()
                    textColor = Color.parseColor("#111111")
                    gravity = Gravity.END or Gravity.CENTER_VERTICAL
                }.lparams(width = 0, height = matchParent, weight = 1f)

                textView("200") {
                    textSize = sp(px2sp(26)).toFloat()
                    textColor = Color.parseColor("#111111")
                    gravity = Gravity.START or Gravity.CENTER_VERTICAL
                }.lparams(width = 0, height = matchParent, weight = 1f)
            }.lparams(width = matchParent, height = dip(65)) { below(MDUI_rl_location) }

            linearLayout {
                id = MDUI_order
                textView("现在来修") {
                    textSize = sp(px2sp(14)).toFloat()
                    textColor = Color.parseColor("#111111")
                    gravity = Gravity.CENTER
                    backgroundColor = Color.parseColor("#fff500")
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = 0, height = matchParent, weight = 9f)

                textView("预约") {
                    textSize = sp(px2sp(26)).toFloat()
                    textColor = Color.parseColor("#111111")
                    gravity = Gravity.CENTER
                    backgroundColor = Color.parseColor("#ff9800")
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams(width = 0, height = matchParent, weight = 5f)
            }.lparams(width = matchParent, height = dip(56)) {
                alignParentBottom()
            }
        }

    }
}