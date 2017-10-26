package com.repair.proj.login.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.repair.proj.R
import org.jetbrains.anko.*

/**
 * Created by Mwh on 2017/10/25.
 */
class LineTimeAdapter(activity: Activity, se: StepEnum) : RecyclerView.Adapter<LineTimeAdapter.LineTimeViewHolder>() {
    var activity = activity
    var step = se.step
    var titles = arrayListOf("个人信息", "照片信息", "人工审核")
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): LineTimeViewHolder {
        var view = activity.layoutInflater.inflate(R.layout.activity_regist_flow_line, null, false)
        return LineTimeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: LineTimeViewHolder?, position: Int) {
        setView(holder, position)
    }

    class LineTimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var lineName: TextView = itemView.find(R.id.arfl_name)
        var lineLeft: TextView = itemView.find(R.id.arfl_left)
        var lineRight: TextView = itemView.find(R.id.arfl_right)
        var lineImage: ImageView = itemView.find(R.id.arfl_image)
    }

    //设置图片的背景相关
    private fun setView(holder: LineTimeViewHolder?, position: Int) = holder?.let {
        //防止重复加载出现控件复用，把控件统一设置一次,虽然这里不可能
        // 普通状态下 设置名称 名称颜色 左右线段颜色，圆形颜色
        it.lineName.text = titles[position]
        it.lineLeft.backgroundResource = R.drawable.line_gray
        it.lineRight.backgroundResource = R.drawable.line_gray
        it.lineName.textColor = activity.resources.getColor(R.color.color_bbbbbb,null)
        it.lineImage.imageResource = R.drawable.point_gray
        //当表示当前状态时
        if (position <= step) {
            //设置字体颜色
            it.lineName.textColor = activity.resources.getColor(R.color.app_second_color)
            //设置左边线段颜色
            it.lineLeft.backgroundResource = R.drawable.line_yellow
            //设置圆形颜色
            it.lineImage.imageResource = R.drawable.point_yellow
        }
        //当前状态之前的右边线需要高亮
        if (position < step) {
            it.lineRight.backgroundResource = R.drawable.line_yellow
        }
        //当最后审核完成时
        if (step == titles.size) {
            //设置字体颜色
            it.lineName.textColor = activity.resources.getColor(R.color.app_second_color)
            //设置左边线段颜色
            it.lineLeft.backgroundResource = R.drawable.line_yellow
            //设置圆形颜色
            it.lineImage.imageResource = R.drawable.point_yellow
            it.lineRight.backgroundResource = R.drawable.line_yellow
        }
    }
}