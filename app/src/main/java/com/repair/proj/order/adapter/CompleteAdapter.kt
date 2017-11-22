package com.repair.proj.order.adapter

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import com.repair.proj.order.entity.MaterialsEntity
import android.widget.TextView
import com.google.gson.annotations.Until
import com.repair.proj.R
import org.jetbrains.anko.find


/**
 * 说明：
 * Created by code_nil on 2017/11/22.
 */
class CompleteAdapter(datas: MutableList<MaterialsEntity>, context: Context) : BaseAdapter(), Filterable {
    var datas = datas
    var context = context
    override fun getItem(position: Int): Any {
        return datas[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return datas.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: ViewHolder? = null
        var view: View? = null
        if (convertView == null) {
            view = View.inflate(context, R.layout.complete_item, null)
            holder = ViewHolder()
            holder.tv_name = view.find(R.id.complete_name)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }
        holder.tv_name?.text = datas[position].name
        return view!!
    }

    override fun getFilter(): Filter {
        return CompleteFilter(datas,this)
    }

    class ViewHolder {
        var tv_name: TextView? = null
    }

    class CompleteFilter(datas: MutableList<MaterialsEntity>, adapter: CompleteAdapter) : Filter() {
        private var datas = datas
        private var adapter = adapter
        private var valus = ArrayList<MaterialsEntity>(datas)
        private var temp = ArrayList<MaterialsEntity>()
        //得到过滤结果
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            temp.clear()
            var results = FilterResults()
            (0 until valus.size)
                    .map { valus[it] }
                    .filter { constraint != null && (it.name.indexOf(constraint.toString())!=-1) }
                    .forEach { temp.add(it) }
            results.values = temp
            results.count = temp.size
            return results
        }

        //发布过滤结果
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results != null) {
                var values = results.values as ArrayList<MaterialsEntity>
                if (values.size > 0) {
                    datas.clear()
                    datas.addAll(values)
                    adapter.notifyDataSetChanged()
                } else {
                    adapter.notifyDataSetInvalidated();
                }
            }
        }

    }
}