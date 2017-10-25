package com.repair.proj.maindetail.presenter

import com.repair.proj.maindetail.contract.LocationContract
import com.repair.proj.maindetail.model.LocationModel

import io.xujiaji.xmvp.presenters.XBasePresenter

/**
 * Created by Mwh on 2017/10/25.
 */

class LocationPresenter : XBasePresenter<LocationContract.View, LocationModel>(), LocationContract.Presenter
