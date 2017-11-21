package com.repair.proj.utils

import android.Manifest
import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.app.AlertDialog
import android.widget.TextView
import com.repair.proj.R
import com.repair.proj.base.Common
import me.iwf.photopicker.PhotoPicker
import me.iwf.photopicker.utils.ImageCaptureManager
import org.jetbrains.anko.dip
import org.jetbrains.anko.find
import org.jetbrains.anko.wrapContent
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

/**
 * 说明：
 * Created by code_nil on 2017/11/21.
 */
class PhotoPickerUtil(activity: Activity) {
    var activity=activity
    var captureManager=ImageCaptureManager(activity)
    var selectPhotos = arrayListOf<String>()
    //相机拍照
    @AfterPermissionGranted(Common.PER_RC_CAMERA)
    fun takeCamera(resultCode:Int) {
        if (EasyPermissions.hasPermissions(activity,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            var intent = captureManager.dispatchTakePictureIntent()
            activity.startActivityForResult(intent,resultCode)
        } else {
            EasyPermissions.requestPermissions(activity, "申请必要权限", Common.PER_RC_CAMERA,
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }

    //相册选择
    @AfterPermissionGranted(Common.PER_RC_ALBUM)
    fun takeAlbum(count: Int,albumCode: Int) {
        if (EasyPermissions.hasPermissions(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            PhotoPicker.builder()
                    .setPhotoCount(count)
                    .setShowCamera(true)
                    .setShowGif(true)
                    .setPreviewEnabled(false)
                    .start(activity,albumCode)
        } else {
            EasyPermissions.requestPermissions(activity, "申请必要权限", Common.PER_RC_ALBUM,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

    }

    fun showTakePhotoDialog(cameraCode:Int,albumCode:Int,albumCount:Int) {
        //显示弹窗
        var dialog:AlertDialog?=null
        var view = activity.layoutInflater.inflate(R.layout.camera_pick, null, false)
        view.find<TextView>(R.id.cp_take_camera).setOnClickListener {
            dialog?.dismiss()
            takeCamera(cameraCode)
        }
        view.find<TextView>(R.id.cp_take_album).setOnClickListener {
            dialog?.dismiss()
            takeAlbum(albumCount,albumCode)
        }
        dialog = AlertDialog.Builder(activity).setView(view).create()
        dialog.show()
        var window = dialog.window
        var params = window.attributes
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        params.width = activity.dip(200)
        params.height = wrapContent
        window.attributes = params
    }

    fun currentPhotoPath():String{
       return  captureManager.currentPhotoPath
    }
}