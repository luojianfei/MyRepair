package com.repair.proj.login

import android.app.Activity
import android.graphics.Typeface
import android.support.v7.widget.LinearLayoutManager
import com.repair.proj.R
import com.repair.proj.login.adapter.LineTimeAdapter
import com.repair.proj.login.adapter.StepEnum
import com.repair.proj.login.contract.RegistSecondContract
import com.repair.proj.login.presenter.RegistSecondPresenter
import com.repair.proj.maindetail.contract.MainDetailContract
import io.xujiaji.xmvp.view.base.XBaseActivity
import kotlinx.android.synthetic.main.activity_regist_second.*
import org.jetbrains.anko.AnkoLogger

/**
 * Created by Mwh on 2017/10/25.
 */
class RegistSecondActivity : XBaseActivity<RegistSecondPresenter>(), RegistSecondContract.View, AnkoLogger {

    override fun beforeSetContentView() {
        super.beforeSetContentView()
    }

    override fun onInit() {
        super.onInit()
        //标题字体
        ars_h_title.typeface = Typeface.DEFAULT_BOLD
        //初始化recylerview
        val layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollHorizontally(): Boolean {
                return false
            }

            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        ars_rv.layoutManager = layoutManager
        ars_rv.adapter = LineTimeAdapter(this, StepEnum.STEP1)
    }

    override fun onListener() {
        super.onListener()
        ars_h_cancle.setOnClickListener { }
        //点击事件展示弹窗
        ars_local_content.setOnClickListener {
            presenter.showLocationPicker(this)
        }
    }

    override fun getContentId(): Int {
        return R.layout.activity_regist_second
    }

}