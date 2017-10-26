package com.repair.proj.login.contract

import android.app.Activity
import com.repair.proj.base.Basefview

import io.xujiaji.xmvp.contracts.XContract

/**
 * Created by nie on 2017/10/23.
 */

interface RegistSecondContract {
    interface View : XContract.View {
    }

    interface Presenter : XContract.Presenter {
        fun showLocationPicker(activity: Activity)
    }

    interface Model : XContract.Model
}
