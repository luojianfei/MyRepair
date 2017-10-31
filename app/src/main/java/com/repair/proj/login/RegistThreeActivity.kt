package com.repair.proj.login

import android.graphics.Typeface
import android.support.v7.widget.LinearLayoutManager
import com.repair.proj.R
import com.repair.proj.databinding.ActivityRegistThreeBinding
import com.repair.proj.login.adapter.LineTimeAdapter
import com.repair.proj.login.adapter.StepEnum
import com.repair.proj.login.contract.RegistThreeContract
import com.repair.proj.login.presenter.RegistThreePresenter
import com.repair.proj.nbase.NActivity
import kotlinx.android.synthetic.main.activity_regist_three.*
import org.jetbrains.anko.startActivity

/**
 * 说明：
 * Created by code_nil on 2017/10/27.
 */

class RegistThreeActivity : NActivity<RegistThreePresenter, ActivityRegistThreeBinding>(), RegistThreeContract.View {

    override fun beforeSetContentView() {
        super.beforeSetContentView()
    }

    override fun onInit() {
        super.onInit()
        art_h_title.typeface = Typeface.DEFAULT_BOLD
        var layoutManager=object : LinearLayoutManager(this){
            override fun canScrollHorizontally(): Boolean {
                return false
            }

            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        layoutManager.orientation=LinearLayoutManager.HORIZONTAL
        art_rv.layoutManager=layoutManager
        art_rv.adapter=LineTimeAdapter(this,StepEnum.STEP2)
    }

    override fun onListener() {
        super.onListener()
        art_next.setOnClickListener {
            startActivity<RegistForeActivity>()
        }
    }

    override fun getContentId(): Int {
        return R.layout.activity_regist_three
    }

}
