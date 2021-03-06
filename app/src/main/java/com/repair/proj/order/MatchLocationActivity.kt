package com.repair.proj.order

import android.content.Context
import com.baidu.mapapi.map.offline.MKOfflineMapListener
import com.repair.proj.R
import com.repair.proj.databinding.ActivityMatchLocationBinding
import com.repair.proj.nbase.NActivity
import com.repair.proj.order.contract.MatchLocationContract
import com.repair.proj.order.presenter.MatchLocationPresenter
import kotlinx.android.synthetic.main.activity_match_location.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.timerTask
import com.baidu.mapapi.model.LatLng
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.repair.proj.location.BdLocationUtil
import com.repair.proj.location.LocationService
import com.repair.proj.location.MyLocationListener
import com.repair.proj.user.CircleCropTransformation
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity


/**
 * 说明：
 * Created by code_nil on 2017/11/14.
 */

class MatchLocationActivity : NActivity<MatchLocationPresenter, ActivityMatchLocationBinding>(), MatchLocationContract.View, AnkoLogger, MKOfflineMapListener {
    override fun showMsg(msg: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun context(): Context {
        return this
    }

    private val format = "mm:ss"
    var timer = Timer()
    var formatter = SimpleDateFormat(format, Locale.CHINA)
    var time: Int = 0
    private var locationService: LocationService? = null
    var listener = MyLocationListener {
        //判断定位类型
        checkLocalType(it.localType)
        var point = LatLng(it.lat, it.lon)
        BdLocationUtil.setUserMapCenter(aml_mv.map, point)
    }

    override fun onInit() {
        //注册地图
        locationService = LocationService(this)
        locationService?.let {
            it.registerListener(listener)
            it.start()
        }

        binding.matchFlag = false
        val serverDrawable = resources.getDrawable(R.drawable.custom_server)
        serverDrawable.setBounds(0, 0, 96, 64)
        aml_cumtom_server.setCompoundDrawables(null, serverDrawable, null, null)

        val callDrawable = resources.getDrawable(R.drawable.call)
        callDrawable.setBounds(0, 0, 80, 80)
        aml_call.setCompoundDrawables(callDrawable, null, null, null)
        aml_call.setPadding(200, 0, 0, 0)

        val chatDrawable = resources.getDrawable(R.drawable.chat)
        chatDrawable.setBounds(0, 0, 80, 80)
        aml_chat.setCompoundDrawables(chatDrawable, null, null, null)
        aml_chat.setPadding(240, 0, 0, 0)
        initTimer()
        aml_mv.map.setMaxAndMinZoomLevel(15f, 15f)

        Glide.with(this).load(R.drawable.header).apply((RequestOptions.bitmapTransform(CircleCropTransformation()))).into(aml_contacts)
    }

    override fun onListener() {
        super.onListener()
        aml_payment.setOnClickListener { startActivity<OrderPayActivity>() }
    }


    override fun onResume() {
        super.onResume()
        aml_mv.onResume()
    }

    override fun onPause() {
        super.onPause()
        aml_mv.onPause()
    }

    private fun initTimer() {
        formatter.timeZone = TimeZone.getTimeZone(getString(R.string.time_gmt))
        timer.schedule(timerTask {
            runOnUiThread {
                time++
                aml_time.text = formatter.format(time * 1000)
                if (time == 5) {
                    binding.matchFlag = true
                    timer.cancel()
                    aml_match_text.text=getString(R.string.aml_matched)
                }
            }
        }, 1000, 1000)
    }

    override fun getContentId(): Int {
        return R.layout.activity_match_location
    }

    override fun onDestroy() {
        super.onDestroy()
        locationService?.let {
            it.stop()
            it.unregisterListener(listener)
        }
        aml_mv.onDestroy()
    }

    private fun checkLocalType(localType: Int) {
        when (localType) {
            62 -> getString(R.string.bd_error_code_62)
            63 -> getString(R.string.bd_error_code_63)
        }
    }

    //离线地图
    override fun onGetOfflineMapState(type: Int, state: Int) {
    }
}
