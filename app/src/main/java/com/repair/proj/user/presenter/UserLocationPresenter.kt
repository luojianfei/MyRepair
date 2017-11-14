package com.repair.proj.user.presenter

import com.repair.proj.user.contract.UserLocationContract
import com.repair.proj.user.model.UserLocationModel
import com.repair.proj.nbase.NPresenter


/**
 * Created by Mwh on 2017/10/25.
 */

class UserLocationPresenter :  NPresenter<UserLocationContract.View, UserLocationModel>(), UserLocationContract.Presenter{

}
