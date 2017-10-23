package com.repair.proj.maindetail

import com.repair.proj.maindetail.contract.MainDetailContract
import com.repair.proj.maindetail.presenter.MainDetailPresenter
import io.xujiaji.xmvp.view.base.XBaseActivity

/**
 * 说明：
 * Created by code_nil on 2017/10/23.
 */

class MainDetailActivity : XBaseActivity<MainDetailPresenter>(), MainDetailContract.View {

    override fun method() {}

    override fun onInit() {
        super.onInit()
    }

    override fun onListener() {
        super.onListener()
    }

    override fun beforeSetContentView() {
        super.beforeSetContentView()
    }


    override fun getContentId(): Int {
        return 1
    }
}
