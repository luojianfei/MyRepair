package com.repair.proj.order

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.repair.proj.R
import android.widget.ArrayAdapter
import com.repair.proj.order.adapter.CompleteAdapter
import com.repair.proj.order.adapter.MaterialsAdapter
import com.repair.proj.order.entity.MaterialsEntity
import kotlinx.android.synthetic.main.activity_order_pay.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import org.jetbrains.anko.startActivity


class OrderPayActivity : AppCompatActivity() ,AnkoLogger{
    var datas = arrayListOf<MaterialsEntity>()
    val searchDatas = mutableListOf(
            MaterialsEntity("aabbaabbaabbaabbaabbaabbaabbaabb", "54", "个","1"),
            MaterialsEntity("abab", "5", "只","1"),
            MaterialsEntity("ab", "5", "双","1"),
            MaterialsEntity("abac", "5", "桶","1"),
            MaterialsEntity("abaa", "5", "瓶","1"),
            MaterialsEntity("baba", "5", "页","1"),
            MaterialsEntity("aabb", "5", "本","1"),
            MaterialsEntity("abab", "5", "支","1"),
            MaterialsEntity("ab", "5", "条","1"),
            MaterialsEntity("abac", "5", "根","1"),
            MaterialsEntity("abaa", "5", "根","1"),
            MaterialsEntity("baba", "5", "根","1"),
            MaterialsEntity("dddd", "5", "根","1"),
            MaterialsEntity("dddd", "5", "根","1"),
            MaterialsEntity("dddd", "5", "根","1"))
    lateinit var adapter2: MaterialsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_pay)

        val adapter = CompleteAdapter(searchDatas, this)
        opa_materials_search.setAdapter(adapter)
        opa_materials_search.setOnItemClickListener { parent, view, position, id ->
            datas.add(searchDatas[position])
            adapter2.notifyDataSetChanged()
            opa_materials_search.setText("")
        }
        opa_materials_search.threshold=1
        var layoutManager = LinearLayoutManager(this)
        adapter2 = MaterialsAdapter(datas, this){
            opa_order_pay_num.text = it.toString()
        }
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        opa_materials_list.layoutManager = layoutManager
        opa_materials_list.adapter = adapter2

        var draw = resources.getDrawable(R.drawable.search)
        draw.setBounds(0, 0, 64, 64)
        opa_materials_search.setCompoundDrawables(null, null, draw, null)
        opa_sure_pay.setOnClickListener { startActivity<MarkOrderActivity>() }
    }
}
