package com.repair.proj.location

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.baidu.mapapi.map.*
import com.baidu.mapapi.model.LatLng
import com.repair.proj.R
import com.repair.proj.entity.LocationEntity
import org.jetbrains.anko.find

/**
 * Created by Mwh on 2017/10/25.
 */
object Util {

    var marker2:Overlay?=null
    fun setUserMapCenter(baiduMap: BaiduMap, le: LocationEntity) {
        var cenpt = LatLng(le.lat, le.lon)
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



    fun setMarker2(baiduMap: BaiduMap, le: LocationEntity, context: Context,level:Int=9) {
        var point = LatLng(le.lat, le.lon)
        //定义Maker坐标点
        var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflater.inflate(R.layout.marker, null)
        view.find<TextView>(R.id.marker_text).text = le.address
        val bp = getBitmapFromView(view)
        val bitmap = BitmapDescriptorFactory.fromBitmap(bp)
//        //构建Marker图标
//        val bitmap = BitmapDescriptorFactory .fromResource(R.drawable.marker)
        //构建MarkerOption，用于在地图上添加Marker
        val option = MarkerOptions()
                .position(point)
                .icon(bitmap)
                .zIndex(level)
                .draggable(true)
        //在地图上添加Marker，并显示
        marker2=baiduMap.addOverlay(option) as Marker
    }

    fun setMarker1(baiduMap: BaiduMap, le: LocationEntity) {
        var point = LatLng(le.lat, le.lon)
        //定义Maker坐标点
//        //构建Marker图标
        val bitmap = BitmapDescriptorFactory .fromResource(R.drawable.marker1)
        //构建MarkerOption，用于在地图上添加Marker
        val option = MarkerOptions()
                .position(point)
                .icon(bitmap)
                .zIndex(8)
        //在地图上添加Marker，并显示
        baiduMap.addOverlay(option)
    }
    private fun getBitmapFromView(view: View): Bitmap {
        view.destroyDrawingCache()
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.UNSPECIFIED);
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        view.isDrawingCacheEnabled = true
        return view.drawingCache
    }

}