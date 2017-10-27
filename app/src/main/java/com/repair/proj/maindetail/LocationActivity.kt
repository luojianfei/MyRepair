package com.repair.proj.maindetail

import com.baidu.location.BDLocationListener
import com.repair.proj.R
import com.repair.proj.databinding.ActivityLocationBinding
import com.repair.proj.location.LocationService
import com.repair.proj.location.MyLocationListener
import com.repair.proj.maindetail.contract.LocationContract
import com.repair.proj.maindetail.presenter.LocationPresenter
import com.repair.proj.nbase.NActivity
import kotlinx.android.synthetic.main.activity_location.*

/**
 * databinding只用于xml数据的绑定
 * Created by Mwh on 2017/10/25.
 */

class LocationActivity : NActivity<LocationPresenter, ActivityLocationBinding>(), LocationContract.View {
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
