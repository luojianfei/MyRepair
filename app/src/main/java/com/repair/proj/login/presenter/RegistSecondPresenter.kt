package com.repair.proj.login.presenter

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.os.Looper
import android.support.v7.app.AlertDialog
import com.repair.proj.login.common.CityCommon
import com.repair.proj.login.contract.RegistSecondContract
import com.repair.proj.login.model.RegistSecondModel
import com.repair.proj.nbase.NPresenter
import com.repair.proj.utils.DialogUtils
import kotlinx.coroutines.experimental.*
import java.util.concurrent.TimeUnit

/**
 * nie
 * Created by nxl on 2017/10/23.
 */

class RegistSecondPresenter : RegistSecondContract.Presenter, NPresenter<RegistSecondContract.View, RegistSecondModel>() {
    private var cityCommon = CityCommon()
    private var dialogUtils = DialogUtils()
    override fun showLocationPicker(activity: Activity) = runBlocking {
        var dialog = ProgressDialog(activity)
        launch(CommonPool) {
            cityCommon.initJsonData(activity)
            dialog.dismiss()
            activity.runOnUiThread {
                cityCommon.showLocationPicker(activity) {
                    view.setLocationData(it)
                }
            }
        }
        dialogUtils.showProgressDialog(dialog, activity)
    }

}
