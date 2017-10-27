package com.repair.proj.maindetail.presenter

import com.repair.proj.maindetail.contract.LocationContract
import com.repair.proj.maindetail.model.LocationModel
import com.repair.proj.nbase.NPresenter


/**
 * Created by Mwh on 2017/10/25.
 */

class LocationPresenter :  NPresenter<LocationContract.View,LocationModel>(),LocationContract.Presenter{

}
