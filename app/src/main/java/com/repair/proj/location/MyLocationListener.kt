package com.repair.proj.location

import com.baidu.location.BDLocation
import com.baidu.location.BDLocationListener
import com.repair.proj.entity.LocationEntity

/**
 * Created by Mwh on 2017/10/25.
 */

class MyLocationListener(action: (entity: LocationEntity) -> Unit) : BDLocationListener {
    var action = action


    override fun onReceiveLocation(location: BDLocation) {
        //Receive Location

        action(LocationEntity(location.latitude, location.longitude, location.radius, location.addrStr, location.time))
//        val sb = StringBuffer(256)
//        sb.append("time : ")
//        sb.append(location.time)
//        sb.append("\nerror code : ")
//        sb.append(location.locType)
//        sb.append("\nlatitude : ")
//        sb.append(location.latitude)
//        sb.append("\nlontitude : ")
//        sb.append(location.longitude)
//        sb.append("\nradius : ")
//        sb.append(location.radius)
//        sb.append("\nradius : ")
//        sb.append(location.radius)
//        if (location.locType == BDLocation.TypeGpsLocation) {// GPS定位结果
//            sb.append("\nspeed : ")
//            sb.append(location.speed)// 单位：公里每小时
//            sb.append("\nsatellite : ")
//            sb.append(location.satelliteNumber)
//            sb.append("\nheight : ")
//            sb.append(location.altitude)// 单位：米
//            sb.append("\ndirection : ")
//            sb.append(location.direction)// 单位度
//            sb.append("\naddr : ")
//            sb.append(location.addrStr)
//            sb.append("\ndescribe : ")
//            sb.append("gps定位成功")
//
//        } else if (location.locType == BDLocation.TypeNetWorkLocation) {// 网络定位结果
//            sb.append("\naddr : ")
//            sb.append(location.addrStr)
//            //运营商信息
//            sb.append("\noperationers : ")
//            sb.append(location.operators)
//            sb.append("\ndescribe : ")
//            sb.append("网络定位成功")
//        } else if (location.locType == BDLocation.TypeOffLineLocation) {// 离线定位结果
//            sb.append("\ndescribe : ")
//            sb.append("离线定位成功，离线定位结果也是有效的")
//        } else if (location.locType == BDLocation.TypeServerError) {
//            sb.append("\ndescribe : ")
//            sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因")
//        } else if (location.locType == BDLocation.TypeNetWorkException) {
//            sb.append("\ndescribe : ")
//            sb.append("网络不同导致定位失败，请检查网络是否通畅")
//        } else if (location.locType == BDLocation.TypeCriteriaException) {
//            sb.append("\ndescribe : ")
//            sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机")
//        }
//        sb.append("\nlocationdescribe : ")
//        sb.append(location.locationDescribe)// 位置语义化信息
//        val list = location.poiList// POI数据
//        if (list != null) {
//            sb.append("\npoilist size = : ")
//            sb.append(list.size)
//            for (p in list) {
//                sb.append("\npoi= : ")
//                sb.append(p.id + " " + p.name + " " + p.rank)
//            }
//        }
//        Log.e("pcw", sb.toString())
    }
}