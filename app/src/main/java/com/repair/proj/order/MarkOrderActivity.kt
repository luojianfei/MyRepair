package com.repair.proj.order

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.repair.proj.R
import com.repair.proj.databinding.ActivityOrderMarkBinding
import com.repair.proj.nbase.NActivity
import com.repair.proj.order.contract.MarkOrderContract
import com.repair.proj.order.presenter.MarkOrderPresenter
import com.repair.proj.user.CircleCropTransformation
import kotlinx.android.synthetic.main.activity_match_location.*
import kotlinx.android.synthetic.main.activity_order_mark.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error

/**
 * 说明：
 * Created by code_nil on 2017/11/22.
 */

class MarkOrderActivity : NActivity<MarkOrderPresenter, ActivityOrderMarkBinding>(), MarkOrderContract.View, AnkoLogger {
    override fun showMsg(msg: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun context(): Context {
        return this ;
    }

    override fun getContentId(): Int {
        return R.layout.activity_order_mark
    }

    override fun onListener() {
        super.onListener()
        aom_rb.isClickable = true
        aom_rb.setStar(2.5f)
        aom_rb.setStepSize(RatingBar.StepSize.Half)//设置每次点击增加一颗星还是半颗星
        aom_rb.setOnRatingChangeListener {
            error { "data-->$it" }
        }

        Glide.with(this).load(R.drawable.header).apply((RequestOptions.bitmapTransform(CircleCropTransformation()))).into(aom_contacts)

        aom_mark.setOnClickListener {
            aom_mark_all.visibility = View.GONE
            aom_user_mark_tip.visibility = View.GONE
            aom_uped_mark_tip.visibility = View.VISIBLE
            aom_server_tip.visibility = View.VISIBLE
            aom_rb.isClickable = false
        }
    }

    override fun method() {

    }
}
