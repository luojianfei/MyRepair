package com.repair.proj.location

import com.baidu.mapapi.map.*
import com.baidu.mapapi.model.LatLng
import com.repair.proj.R

/**
 * Created by Mwh on 2017/10/25.
 */
class Util {
    private fun setUserMapCenter(baiduMap: BaiduMap, cenpt: LatLng) {
        //        LatLng cenpt = new LatLng(lat,lon);
        //定义地图状态
        val mMapStatus = MapStatus.Builder()
                .target(cenpt)
                .zoom(18f)
                .build()
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        val mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus)
        //改变地图状态
        baiduMap.setMapStatus(mMapStatusUpdate)
    }

    private fun setMarker(baiduMap: BaiduMap, point: LatLng) {
        //定义Maker坐标点
        //        LatLng point = new LatLng(lat, lon);
        //构建Marker图标
        val bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.point_yellow)
        //构建MarkerOption，用于在地图上添加Marker
        val option = MarkerOptions()
                .position(point)
                .icon(bitmap)
        //在地图上添加Marker，并显示
        baiduMap.addOverlay(option)
    }

}