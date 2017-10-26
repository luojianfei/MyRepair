package com.repair.proj.login.presenter

import android.app.Activity
import android.support.v7.app.AlertDialog
import com.repair.proj.login.common.CityCommon
import com.repair.proj.login.contract.RegistSecondContract
import com.repair.proj.login.model.RegistSecondModel
import io.xujiaji.xmvp.presenters.XBasePresenter
import kotlinx.coroutines.experimental.*

/**
 * Created by HX·罗 on 2017/10/23.
 */

class RegistSecondPresenter : XBasePresenter<RegistSecondContract.View, RegistSecondModel>(), RegistSecondContract.Presenter {
    var cityCommon = CityCommon()
    override fun showLocationPicker(activity: Activity) = runBlocking {
        //show loading
        var dialog = AlertDialog.Builder(activity).setTitle("加载").setMessage("等待城市数据加载").create()
        dialog.show()
        var job = async(CommonPool) {
            cityCommon.initJsonData(activity)
        }
        job.join()
        dialog.dismiss()
        cityCommon.showLocationPicker(activity)
    }

}
