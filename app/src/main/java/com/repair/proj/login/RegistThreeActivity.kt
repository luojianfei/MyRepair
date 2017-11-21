package com.repair.proj.login

import android.Manifest
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.support.v4.content.FileProvider
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.TransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.repair.proj.R
import com.repair.proj.base.Common
import com.repair.proj.base.Common.ALBUM_ID_BACK
import com.repair.proj.base.Common.ALBUM_ID_FONT
import com.repair.proj.base.Common.ALBUM_ID_HANDLE
import com.repair.proj.base.Common.PHOTO_ID_BACK
import com.repair.proj.base.Common.PHOTO_ID_FONT
import com.repair.proj.base.Common.PHOTO_ID_HANDLE
import com.repair.proj.base.Common.PREVIEW_ID_BACK
import com.repair.proj.base.Common.PREVIEW_ID_FONT
import com.repair.proj.base.Common.PREVIEW_ID_HANDLE
import com.repair.proj.databinding.ActivityRegistThreeBinding
import com.repair.proj.login.adapter.LineTimeAdapter
import com.repair.proj.login.adapter.StepEnum
import com.repair.proj.login.contract.RegistThreeContract
import com.repair.proj.login.presenter.RegistThreePresenter
import com.repair.proj.nbase.NActivity
import com.repair.proj.utils.CameraUtil
import com.repair.proj.utils.PhotoPickerUtil
import kotlinx.android.synthetic.main.activity_regist_three.*
import me.iwf.photopicker.PhotoPicker
import me.iwf.photopicker.PhotoPreview
import me.iwf.photopicker.utils.ImageCaptureManager
import org.jetbrains.anko.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.io.File

/**
 * 说明：
 * Created by code_nil on 2017/10/27.
 */

class RegistThreeActivity : NActivity<RegistThreePresenter, ActivityRegistThreeBinding>(), RegistThreeContract.View, EasyPermissions.PermissionCallbacks,View.OnClickListener {
    private lateinit var ppu:PhotoPickerUtil
    private lateinit var options:RequestOptions
    private var backList= arrayListOf<String>()
    private var fontList= arrayListOf<String>()
    private var handleList= arrayListOf<String>()
    override fun onInit() {
        super.onInit()
        ppu=PhotoPickerUtil(this)
        options= RequestOptions()
        //占位
        options.placeholder(R.mipmap.ic_launcher)
        //大图加载到小图需要resize
        options.override(222,136)
        art_h_title.typeface = Typeface.DEFAULT_BOLD
        var layoutManager = object : LinearLayoutManager(this) {
            override fun canScrollHorizontally(): Boolean {
                return false
            }

            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        art_rv.layoutManager = layoutManager
        art_rv.adapter = LineTimeAdapter(this, StepEnum.STEP2)
    }


    override fun getContentId(): Int {
        return R.layout.activity_regist_three
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
            //拍照返回
                PHOTO_ID_FONT -> {
                    var path=ppu.currentPhotoPath()
                    if(!TextUtils.isEmpty(path)){
                        fontList.clear()
                        fontList.add(path)
                        Glide.with(this).load(fontList[0]).apply(options).into(art_idcard_font)
                    }
                }

                PHOTO_ID_BACK -> {
                    var path=ppu.currentPhotoPath()
                    if(!TextUtils.isEmpty(path)){
                        backList.clear()
                        backList.add(path)
                        Glide.with(this).load(backList[0]).apply(options).into(art_idcard_back)
                    }
                }

                PHOTO_ID_HANDLE -> {
                    var path=ppu.currentPhotoPath()
                    if(!TextUtils.isEmpty(path)){
                        handleList.clear()
                        handleList.add(path)
                        Glide.with(this).load(handleList[0]).apply(options).into(art_idcard_handheld)
                    }
                }

                ALBUM_ID_FONT->{
                    if (data != null) {
                        var checkPhotos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS)
                        fontList.clear()
                        fontList.addAll(checkPhotos)
                        if(checkPhotos.size==1){
                            Glide.with(this).load(fontList[0]).apply(options).into(art_idcard_font)
                        }
                    }
                }

                ALBUM_ID_BACK->{
                    if (data != null) {
                        var checkPhotos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS)
                        backList.clear()
                        backList.addAll(checkPhotos)
                        if(checkPhotos.size==1){
                            Glide.with(this).load(backList[0]).apply(options).into(art_idcard_back)
                        }
                    }
                }

                ALBUM_ID_HANDLE->{
                    if (data != null) {
                        var checkPhotos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS)
                        handleList.clear()
                        handleList.addAll(checkPhotos)
                        if(checkPhotos.size==1){
                            Glide.with(this).load(handleList[0]).apply(options).into(art_idcard_handheld)
                        }
                    }
                }

                PREVIEW_ID_FONT->{
                    if (data != null) {
                        fontList.clear()
                        art_idcard_font.setImageResource(R.drawable.upload_add)
                    }
                }

                PREVIEW_ID_BACK->{
                    if (data != null) {
                        backList.clear()
                        art_idcard_back.setImageResource(R.drawable.upload_add)
                    }
                }

                PREVIEW_ID_HANDLE->{
                    if (data != null) {
                        handleList.clear()
                        art_idcard_handheld.setImageResource(R.drawable.upload_add)
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>?) {
        AppSettingsDialog.Builder(this).build().show()
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>?) {

    }

    override fun onClick(v: View) {
        when (v.id){

            R.id.art_next->{
                startActivity<RegistForeActivity>()
            }

            R.id.art_idcard_ll_font->{
                if(fontList.size==0){
                    ppu.showTakePhotoDialog(PHOTO_ID_FONT, ALBUM_ID_FONT,1)
                }else{
                    showDetail(fontList, PREVIEW_ID_FONT)
                }
            }

            R.id.art_idcard_ll_back->{
                if(backList.size==0){
                    ppu.showTakePhotoDialog(PHOTO_ID_BACK, ALBUM_ID_BACK,1)
                }else{
                    showDetail(backList, PREVIEW_ID_BACK)
                }
            }

            R.id.art_idcard_ll_handheld->{
                if(handleList.size==0){
                    ppu.showTakePhotoDialog(PHOTO_ID_HANDLE, ALBUM_ID_HANDLE,1)
                }else{
                    showDetail(handleList, PREVIEW_ID_HANDLE)
                }
            }
        }
    }

    private fun showDetail(selectPhotos:ArrayList<String>,requestCode:Int){
        PhotoPreview.builder()
                .setPhotos(selectPhotos)
                .setCurrentItem(0)
                .setShowDeleteButton(true)
                .start(this, requestCode)
    }
}
