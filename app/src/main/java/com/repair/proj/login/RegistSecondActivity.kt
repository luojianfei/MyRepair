package com.repair.proj.login

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.LinearLayoutManager
import com.repair.proj.R
import com.repair.proj.databinding.ActivityRegistSecondBinding
import com.repair.proj.login.adapter.LineTimeAdapter
import com.repair.proj.login.adapter.StepEnum
import com.repair.proj.login.contract.RegistSecondContract
import com.repair.proj.login.entity.SecondEnitity
import com.repair.proj.login.presenter.RegistSecondPresenter
import com.repair.proj.nbase.NActivity
import kotlinx.android.synthetic.main.activity_regist_second.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity

/**
 * databinding只用于xml数据的绑定
 * Created by Mwh on 2017/10/25.
 */
class RegistSecondActivity : NActivity<RegistSecondPresenter, ActivityRegistSecondBinding>(), RegistSecondContract.View, AnkoLogger {
    override fun showMsg(msg: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun context(): Context {
        return this ;
    }

    var se = SecondEnitity()
    override fun beforeSetContentView() {
        super.beforeSetContentView()
    }

    override fun onInit() {
        super.onInit()
        binding.secondEntity = se
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
        ars_next.setOnClickListener {
            startActivity<RegistThreeActivity>()
        }
    }

    override fun getContentId(): Int {
        return R.layout.activity_regist_second
    }

    override fun setLocationData(data: String?) {
        se.address = data ?: ""
    }

    override fun getRegistSecondData(): SecondEnitity {
        return se
    }
}