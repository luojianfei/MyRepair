package com.repair.proj.maindetail.presenter

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.bigkoo.pickerview.OptionsPickerView
import com.repair.proj.R
import com.repair.proj.base.Common
import com.repair.proj.maindetail.contract.MainDetailContract
import com.repair.proj.maindetail.model.MainDetailModel
import com.repair.proj.nbase.NPresenter
import kotlinx.android.synthetic.main.activity_main_detail.*
import org.jetbrains.anko.toast
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import java.io.File
import java.util.ArrayList


/**
 * 说明：
 * Created by code_nil on 2017/10/23.
 */

class MainDetailPresenter : NPresenter<MainDetailContract.View, MainDetailModel>(), MainDetailContract.Presenter {

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
