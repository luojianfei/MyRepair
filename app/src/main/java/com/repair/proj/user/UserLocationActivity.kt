package com.repair.proj.user

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.inputmethod.EditorInfo
import com.baidu.location.BDLocationListener
import com.baidu.mapapi.map.BaiduMap
import com.baidu.mapapi.map.MapStatus
import com.baidu.mapapi.model.LatLng
import com.baidu.mapapi.search.geocode.*
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener
import com.baidu.mapapi.search.sug.SuggestionResult
import com.baidu.mapapi.search.sug.SuggestionSearch
import com.baidu.mapapi.search.sug.SuggestionSearchOption
import com.repair.proj.R
import com.repair.proj.databinding.ActivityUserLocationBinding
import com.repair.proj.location.LocationService
import com.repair.proj.location.MyLocationListener
import com.repair.proj.location.BdLocationUtil
import com.repair.proj.user.contract.UserLocationContract
import com.repair.proj.user.presenter.UserLocationPresenter
import com.repair.proj.nbase.NActivity
import kotlinx.android.synthetic.main.activity_user_location.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error

/**
 * databinding只用于xml数据的绑定
 * Created by Mwh on 2017/10/25.
 */

class UserLocationActivity : NActivity<UserLocationPresenter, ActivityUserLocationBinding>(), UserLocationContract.View, AnkoLogger, OnGetSuggestionResultListener {
    override fun showMsg(msg: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun context(): Context {
        return this
    }

    override val loggerTag: String
        get() = "ttttt"
    private var locationService: LocationService? = null
    var geoCoder = GeoCoder.newInstance()
    var reverseGeoCodeOption = ReverseGeoCodeOption()
    var mSuggestionSearch = SuggestionSearch.newInstance()
    var cityCode = ""
    private var myListener = MyLocationListener {
        var point = LatLng(it.lat, it.lon)
        BdLocationUtil.setUserMapCenter(al_bd_mapview.map, point)
        BdLocationUtil.setMarker1(al_bd_mapview.map, point)
        al_address.text = it.address
        al_city.text = it.city
        cityCode = it.cityCode
    }

    override fun getContentId(): Int {
        return R.layout.activity_user_location
    }

    override fun onInit() {
        super.onInit()
        //声明LocationClient类
        locationService = LocationService(applicationContext)
        //注册定位监听函数
        locationService?.registerListener(myListener)
        //开始定位
        locationService?.start()

        al_bd_mapview.map.isMyLocationEnabled = false

        mSuggestionSearch.setOnGetSuggestionResultListener(this)
    }

    override fun onListener() {
        super.onListener()
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

        al_relocation.setOnClickListener { locationService?.triggerLocation() }

        al_location_search.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                var keys = textView.text.toString()
                var city = al_city.text.toString()
                if (!TextUtils.isEmpty(keys) && !TextUtils.isEmpty(city)) {
                    mSuggestionSearch.requestSuggestion(SuggestionSearchOption().keyword(keys).city(city))
                }
            }
            false
        }
    }

    override fun onPause() {
        super.onPause()
        al_bd_mapview.onPause()
    }

    override fun onResume() {
        super.onResume()
        al_bd_mapview.onResume()
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
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onGetSuggestionResult(p0: SuggestionResult?) {
        if (p0 != null && p0.allSuggestions != null && p0.allSuggestions.size > 0) {
            var point = LatLng(p0.allSuggestions[0].pt.latitude, p0.allSuggestions[0].pt.longitude)
            BdLocationUtil.setUserMapCenter(al_bd_mapview.map, point)
            BdLocationUtil.setMarker1(al_bd_mapview.map, point)
            error { p0.toString() }
        }
    }
}
