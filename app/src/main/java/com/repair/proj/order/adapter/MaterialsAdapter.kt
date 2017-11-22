package com.repair.proj.order.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.repair.proj.R
import com.repair.proj.order.entity.MaterialsEntity
import org.jetbrains.anko.find

/**
 * 说明：
 * Created by code_nil on 2017/11/21.
 */
class MaterialsAdapter(datas: ArrayList<MaterialsEntity>, activity: Activity, action: (Float) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var action = action
    val TYPE_HEADER = 1
    val TYPE_NORMAL = 2
    var datas = datas
    var activity = activity
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_HEADER) {
            var headerView = activity.layoutInflater.inflate(R.layout.materials_item_header, parent, false)
            return MaterialsViewHeaderHolder(headerView)
        }
        var normalView = activity.layoutInflater.inflate(R.layout.materials_item, parent, false)
        var normalHeader = MaterialsViewHolder(normalView)
        normalHeader.num.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s != null && s.isNotEmpty()) {
                    var result=0f
                    ( 0 until datas.size)
                            .map { datas[it]}
                            .map { it.num.toFloat()*it.price.toFloat() }
                            .map { result+=it }
                    action(result)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

        })
        return normalHeader
    }

    override fun getItemCount(): Int {
        return datas.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_HEADER
        }
        return TYPE_NORMAL
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        holder.let {
            if (holder!!.itemViewType == TYPE_NORMAL) {
                var poi=position-1
                var normalHolder = holder as MaterialsViewHolder
                normalHolder.name.text = datas[poi].name
                normalHolder.unit.text = datas[poi].unit
                normalHolder.price.text = datas[poi].price
                normalHolder.num.text = "1"
                normalHolder.itemView.tag = poi
            }
        }
    }


    class MaterialsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.find<TextView>(R.id.mi_name)
        var unit = itemView.find<TextView>(R.id.mi_unit)
        var price = itemView.find<TextView>(R.id.mi_price)
        var num = itemView.find<TextView>(R.id.mi_num)
    }

    class MaterialsViewHeaderHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}