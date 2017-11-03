package com.repair.proj.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import com.repair.proj.MainActivity
import com.repair.proj.R
import com.repair.proj.base.Common
import com.repair.proj.utils.ActivityUtils.startActivityForResult
import org.jetbrains.anko.toast
import java.io.File

/**
 * 说明：
 * Created by code_nil on 2017/11/3.
 */
object CameraUtil {
    fun takeCamera(activity: Activity,uri: Uri,code:Int) {
        var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        try {
            activity.startActivityForResult(intent,code)
        } catch (anf: ActivityNotFoundException) {
            activity.toast("摄像头未准备好!")
        }
    }
}