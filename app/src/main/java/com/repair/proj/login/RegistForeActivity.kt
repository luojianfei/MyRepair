package com.repair.proj.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.repair.proj.R
import com.repair.proj.login.adapter.LineTimeAdapter
import com.repair.proj.login.adapter.StepEnum
import kotlinx.android.synthetic.main.activity_regist_fore.*

/**
 * 说明：
 * Created by code_nil on 2017/10/30.
 */
class RegistForeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regist_fore)
        var layoutManager=object : LinearLayoutManager(this){
            override fun canScrollHorizontally(): Boolean {
                return false
            }

            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        layoutManager.orientation= LinearLayoutManager.HORIZONTAL
        arf_rv.layoutManager=layoutManager
        arf_rv.adapter= LineTimeAdapter(this, StepEnum.STEP3)
    }
}