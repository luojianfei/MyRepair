package com.repair.proj.user.presenter

import android.app.Activity
import android.graphics.Color
import android.util.Log
import android.view.ViewGroup
import com.bigkoo.pickerview.OptionsPickerView
import com.repair.proj.R
import com.repair.proj.user.contract.UserDetailContract
import com.repair.proj.user.model.UserDetailModel
import com.repair.proj.nbase.NPresenter
import java.util.ArrayList


/**
 * 说明：
 * Created by code_nil on 2017/10/23.
 */

class UserDetailPresenter : NPresenter<UserDetailContract.View, UserDetailModel>(), UserDetailContract.Presenter {

    private val cardItem = ArrayList<String>()
    private var pvCustomOptions: OptionsPickerView<*>? = null

    override fun start() {
        super.start()
        //从model中获取
        getCardData()
    }

    override fun showOrDismissDetailPicker(isShow: Boolean) {
        if (isShow) {
            pvCustomOptions?.show(false)
        } else {
            pvCustomOptions?.dismiss()
        }

    }

    fun initCustomOptionPicker(viewGroup: ViewGroup, activity: Activity) {//条件选择器初始化，自定义布局
        /**
         * @description
         *
         * 注意事项：
         * 自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针。
         * 具体可参考demo 里面的两个自定义layout布局。
         */
        pvCustomOptions = OptionsPickerView.Builder(activity, OptionsPickerView.OnOptionsSelectListener { options1, _, _, _ ->
            //返回的分别是三个级别的选中位置
            Log.e("ttttt", cardItem[options1] + "")
        })
                .setLayoutRes(R.layout.pickerview_custom_options) { }
                .setDecorView(viewGroup)
                .setSelectOptions(9)
                .setBgColor(Color.parseColor("#fafafa"))
                .setLineSpacingMultiplier(1.4f)
                .build()
        pvCustomOptions?.setPicker(cardItem)//添加数据
    }

    private fun getCardData() {
        (0..19).mapTo(cardItem) { "" + it }
    }

}
