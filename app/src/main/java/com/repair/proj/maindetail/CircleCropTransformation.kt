package com.repair.proj.maindetail

import android.graphics.*
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest
import android.graphics.BitmapShader
import android.graphics.Bitmap



/**
 * 说明：
 * Created by code_nil on 2017/11/7.
 */
class CircleCropTransformation : BitmapTransformation() {


    override fun transform(pool: BitmapPool, inBitmap: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        val size = Math.min(inBitmap.width, inBitmap.height)
        val x = (inBitmap.width - size) / 2
        val y = (inBitmap.height - size) / 2
        // TODO this could be acquired from the pool too
        val squared = Bitmap.createBitmap(inBitmap, x, y, size, size)
        var result = pool.get(size, size, Bitmap.Config.ARGB_8888)
        if (result == null) {
            result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        }
        val canvas = Canvas(result)
        val paint = Paint()
        paint.shader = BitmapShader(squared, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.isAntiAlias = true

        //绘制边框
        val mBorderPaint = Paint()
        mBorderPaint.style = Paint.Style.STROKE
        mBorderPaint.strokeWidth = 4f//画笔宽度为4px
        mBorderPaint.color = Color.parseColor("#bbbbbb")//边框颜色
        mBorderPaint.strokeCap = Paint.Cap.ROUND
        mBorderPaint.isAntiAlias = true
        val r = size / 2f
        val r1 = (size - 2 * 4) / 2f
        canvas.drawCircle(r, r, r1, paint)
        canvas.drawCircle(r, r, r1, mBorderPaint)//画边框
        return result
    }



    override fun updateDiskCacheKey(messageDigest: MessageDigest?) {
    }


}