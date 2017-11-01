package com.repair.proj.login.common

import android.app.Activity
import android.graphics.Color
import android.util.Log
import android.view.View

import com.bigkoo.pickerview.OptionsPickerView
import com.google.gson.Gson

import org.json.JSONArray

import java.util.ArrayList

/**
 * Created by Mwh on 2017/10/25.
 */

class CityCommon {
    private var options1Items: List<JsonBean> = ArrayList()
    private val options2Items = ArrayList<ArrayList<String>>()
    private val options3Items = ArrayList<ArrayList<ArrayList<String>>>()

    fun initJsonData(activity: Activity) {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         */
        val JsonData = GetJsonDataUtil().getJson(activity, "province.json")//获取assets目录下的json文件数据
        val jsonBean = parseData(JsonData)//用Gson 转成实体
        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean

        for (i in jsonBean.indices) {//遍历省份
            val CityList = ArrayList<String>()//该省的城市列表（第二级）
            val Province_AreaList = ArrayList<ArrayList<String>>()//该省的所有地区列表（第三极）

            for (c in 0 until jsonBean[i].cityList.size) {//遍历该省份的所有城市
                val CityName = jsonBean[i].cityList[c].name
                CityList.add(CityName)//添加城市

                val City_AreaList = ArrayList<String>()//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean[i].cityList[c].area == null || jsonBean[i].cityList[c].area.size == 0) {
                    City_AreaList.add("")
                } else {

                    for (d in 0 until jsonBean[i].cityList[c].area.size) {//该城市对应地区所有数据
                        val AreaName = jsonBean[i].cityList[c].area[d]

                        City_AreaList.add(AreaName)//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList)//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList)

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList)
        }
    }

    private fun parseData(result: String): ArrayList<JsonBean> {//Gson 解析
        val detail = ArrayList<JsonBean>()
        try {
            val data = JSONArray(result)
            val gson = Gson()
            for (i in 0 until data.length()) {
                try {
                    val entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean::class.java)
                    detail.add(entity)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return detail
    }

    fun showLocationPicker(activity: Activity, action: (data: String) -> Unit) {// 弹出选择器

        val pvOptions = OptionsPickerView.Builder(activity, OptionsPickerView.OnOptionsSelectListener { options1, options2, options3, v ->
            //返回的分别是三个级别的选中位置
            val tx = options1Items[options1].pickerViewText + "," +
                    options2Items[options1][options2] + "," +
                    options3Items[options1][options2][options3]
            action(tx)
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(false)// default is true
                .build()
        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items)//三级选择器
        pvOptions.show()
    }
}
