package com.repair.proj.user.adapter

import android.app.Activity
import android.media.Image
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.repair.proj.R
import com.repair.proj.base.Common
import me.iwf.photopicker.PhotoPreview
import org.jetbrains.anko.find

/**
 * 说明：
 * Created by code_nil on 2017/11/20.
 */
class SelectPictureAdapter(selectPhotos: ArrayList<String>, activity: Activity) : RecyclerView.Adapter<SelectPictureAdapter.SPViewHolder>(), View.OnClickListener {
    var options=RequestOptions()
    var selectPhotos = selectPhotos
    var activity = activity
    init {
        options.placeholder(R.mipmap.ic_launcher)
        //大图加载到小图需要resize
        options.override(136,136)
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SPViewHolder {
        var view = activity.layoutInflater.inflate(R.layout.select_picture_item, null, false)
        view.setOnClickListener(this)
        return SPViewHolder(view)
    }

    override fun onBindViewHolder(holder: SPViewHolder?, position: Int) {
        holder?.let {
            holder.imageView.tag=null
            var path=selectPhotos[position]
            Glide.with(activity).load(path).apply(options).into(holder.imageView)
            holder.imageView.setTag(R.id.positionTag,position)
        }
    }

    override fun getItemCount(): Int {
        return selectPhotos.size
    }

    override fun onClick(v: View?) {
        v.let {
            var imageView=v!!.find<ImageView>(R.id.select_picture_item)
            var position= imageView.getTag(R.id.positionTag).toString().toInt()
            PhotoPreview.builder()
                    .setPhotos(selectPhotos)
                    .setCurrentItem(position)
                    .setShowDeleteButton(true)
                    .start(activity,Common.PHOTO_PREVIEW)
        }
    }


    class SPViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView = itemView.find<ImageView>(R.id.select_picture_item)
    }
}