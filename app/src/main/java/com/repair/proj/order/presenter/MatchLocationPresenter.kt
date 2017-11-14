package com.repair.proj.order.presenter

import com.repair.proj.nbase.NPresenter
import com.repair.proj.order.contract.MatchLocationContract
import com.repair.proj.order.model.MatchLocationModel

/**
 * 说明：
 * Created by code_nil on 2017/11/14.
 */

class MatchLocationPresenter : NPresenter<MatchLocationContract.View, MatchLocationModel>(), MatchLocationContract.Presenter
