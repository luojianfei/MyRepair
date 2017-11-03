package com.repair.proj.utils

import android.app.Activity
import android.graphics.Bitmap
import android.net.Uri
import com.repair.proj.R
import com.yalantis.ucrop.UCrop
import java.io.File

/**
 * 说明：
 * Created by code_nil on 2017/11/3.
 */
object CropUtil {
    //裁剪图片
    fun cropRawPhoto(activity: Activity, srcUri: Uri, toUri: Uri,code:Int) {
        // 修改配置参数（我这里只是列出了部分配置，并不是全部）
        var options = UCrop.Options()
        // 修改标题栏颜色
        options.setToolbarColor(activity.resources.getColor(R.color.colorPrimary))
        // 修改状态栏颜色
        options.setStatusBarColor(activity.resources.getColor(R.color.colorPrimaryDark))
        // 隐藏底部工具
        options.setHideBottomControls(true)
        // 图片格式
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG)
        // 设置图片压缩质量
        options.setCompressionQuality(100)
        // 是否让用户调整范围(默认false)，如果开启，可能会造成剪切的图片的长宽比不是设定的
        // 如果不开启，用户不能拖动选框，只能缩放图片
        options.setFreeStyleCropEnabled(true)

        // 设置源uri及目标uri
        UCrop.of(srcUri, toUri)
                // 长宽比
                .withAspectRatio(16f, 9f)
                // 图片大小
                .withMaxResultSize(200, 200)
                // 配置参数
                .withOptions(options)
                .start(activity,code)
    }
}