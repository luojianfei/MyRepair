package com.repair.proj.maindetail

import android.widget.Button
import com.baidu.location.BDLocationListener
import com.baidu.mapapi.map.BaiduMap
import com.baidu.mapapi.map.MapView
import com.repair.proj.R
import com.repair.proj.location.LocationService
import com.repair.proj.location.MyLocationListener
import com.repair.proj.maindetail.contract.LocationContract
import com.repair.proj.maindetail.presenter.LocationPresenter

import io.xujiaji.xmvp.view.base.XBaseActivity
import kotlinx.android.synthetic.main.activity_location.*

/**
 * Created by Mwh on 2017/10/25.
 */

class LocationActivity : XBaseActivity<LocationPresenter>(), LocationContract.View {
    private var locationService: LocationService? = null
    private var myListener: BDLocationListener = MyLocationListener()
    override fun getContentId(): Int {
        return R.layout.activity_location
    }

    override fun onInit() {
        super.onInit()
        //声明LocationClient类
        locationService = LocationService(applicationContext)
        //注册监听函数
        locationService?.registerListener(myListener)
        //开始定位
        locationService?.start()
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

}
