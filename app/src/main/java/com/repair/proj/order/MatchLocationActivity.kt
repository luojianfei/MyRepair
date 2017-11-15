package com.repair.proj.order

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView

import com.repair.proj.R
import com.repair.proj.databinding.ActivityMatchLocationBinding
import com.repair.proj.nbase.NActivity
import com.repair.proj.order.contract.MatchLocationContract
import com.repair.proj.order.presenter.MatchLocationPresenter
import kotlinx.android.synthetic.main.activity_match_location.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.timerTask
import android.view.View.MeasureSpec
import android.view.View.MeasureSpec.UNSPECIFIED
import android.view.View.MeasureSpec.makeMeasureSpec



/**
 * 说明：
 * Created by code_nil on 2017/11/14.
 */

class MatchLocationActivity : NActivity<MatchLocationPresenter, ActivityMatchLocationBinding>(), MatchLocationContract.View {

    var timer = Timer()
    private val format = "mm:ss"
    var formatter = SimpleDateFormat(format, Locale.CHINA)
    var time: Int = 0
    override fun onInit() {
        val serverDrawable = resources.getDrawable(R.drawable.custom_server)
        serverDrawable.setBounds(0, 0, 96, 64)
        aml_cumtom_server.setCompoundDrawables(null, serverDrawable, null, null)

        val callDrawable = resources.getDrawable(R.drawable.call)
        callDrawable.setBounds(0, 0, 80, 80)
        aml_call.setCompoundDrawables(callDrawable,null, null, null)
        aml_call.setPadding(200,0,0,0)

        val chatDrawable = resources.getDrawable(R.drawable.chat)
        chatDrawable.setBounds(0, 0, 80, 80)
        aml_chat.setCompoundDrawables(chatDrawable,null, null, null)
        aml_chat.setPadding(240,0,0,0)
        initTimer()
    }

    private fun initTimer() {
        formatter.timeZone = TimeZone.getTimeZone("GMT+00:00")
        timer.schedule(timerTask {
            runOnUiThread {
                time++
                aml_time.text = formatter.format(time * 1000)
            }
        }, 1000,1000)
    }

    override fun getContentId(): Int {
        return R.layout.activity_match_location
    }

}
