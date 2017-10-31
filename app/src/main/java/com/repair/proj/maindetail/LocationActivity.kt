package com.repair.proj.maindetail

import android.content.Intent
import android.text.TextUtils
import com.baidu.location.BDLocationListener
import com.baidu.mapapi.map.BaiduMap
import com.baidu.mapapi.map.MapStatus
import com.baidu.mapapi.search.geocode.*
import com.repair.proj.R
import com.repair.proj.base.Common
import com.repair.proj.databinding.ActivityLocationBinding
import com.repair.proj.location.LocationService
import com.repair.proj.location.MyLocationListener
import com.repair.proj.location.Util
import com.repair.proj.maindetail.contract.LocationContract
import com.repair.proj.maindetail.presenter.LocationPresenter
import com.repair.proj.nbase.NActivity
import kotlinx.android.synthetic.main.activity_location.*
import kotlinx.android.synthetic.main.activity_test.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error

/**
 * databinding只用于xml数据的绑定
 * Created by Mwh on 2017/10/25.
 */

class LocationActivity : NActivity<LocationPresenter, ActivityLocationBinding>(), LocationContract.View, AnkoLogger {
    override val loggerTag: String
        get() = "ttttt"
    private var locationService: LocationService? = null
    var geoCoder = GeoCoder.newInstance()
    var reverseGeoCodeOption = ReverseGeoCodeOption()
    private var myListener: BDLocationListener = MyLocationListener {
        Util.setUserMapCenter(al_bd_mapview.map, it)
        Util.setMarker1(al_bd_mapview.map, it)
        al_address.text = it.address
    }

    override fun getContentId(): Int {
        return R.layout.activity_location
    }

    override fun onInit() {
        super.onInit()
        //声明LocationClient类
        locationService = LocationService(applicationContext)

        al_bd_mapview.map.isMyLocationEnabled = false

    }

    override fun onListener() {
        super.onListener()
        //注册定位监听函数
        locationService?.registerListener(myListener)
        //开始定位
        locationService?.start()
        //注册位置改变监听函数
        al_bd_mapview.map.setOnMapStatusChangeListener(object : BaiduMap.OnMapStatusChangeListener {
            override fun onMapStatusChangeStart(p0: MapStatus?) {
                error { "onMapStatusChangeStart" }
            }

            override fun onMapStatusChangeStart(p0: MapStatus?, p1: Int) {
                error { "onMapStatusChangeStart" }
            }

            override fun onMapStatusChange(p0: MapStatus?) {
                error { "onMapStatusChange" }
            }

            override fun onMapStatusChangeFinish(mapStatus: MapStatus?) {
                if (mapStatus != null) {
                    geoCoder.reverseGeoCode(reverseGeoCodeOption.location(mapStatus.target))
                    geoCoder.setOnGetGeoCodeResultListener(object : OnGetGeoCoderResultListener {
                        override fun onGetGeoCodeResult(p0: GeoCodeResult?) {
                            error { p0?.address ?: "error" }
                        }

                        override fun onGetReverseGeoCodeResult(p0: ReverseGeoCodeResult?) {
                            al_address.text = p0?.address ?: "地理位置获取失败"
                        }
                    })
                }
            }

        })
        al_sure.setOnClickListener { backResult() }
    }

    override fun onPause() {
        super.onPause()
        al_bd_mapview.onPause()
    }

    override fun onResume() {
        super.onResume()
        al_bd_mapview.onResume()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        locationService?.let {
            it.stop()
            it.unregisterListener(myListener)
        }
    }

    private fun backResult() {
        var intent = Intent()
        intent.putExtra("address", al_address.text)
        setResult(Common.LOCATION_RESULT_CODE, intent)
        finish()
    }
}
