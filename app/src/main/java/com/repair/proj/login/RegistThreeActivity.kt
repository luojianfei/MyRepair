package com.repair.proj.login

import android.Manifest
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.support.v4.content.FileProvider
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.TransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.repair.proj.R
import com.repair.proj.base.Common
import com.repair.proj.base.Common.PHOTO_ID_BACK
import com.repair.proj.base.Common.PHOTO_ID_FONT
import com.repair.proj.base.Common.PHOTO_ID_HANDLE
import com.repair.proj.base.Common.UCROP_ID_BACK
import com.repair.proj.base.Common.UCROP_ID_FONT
import com.repair.proj.base.Common.UCROP_ID_HANDLE
import com.repair.proj.databinding.ActivityRegistThreeBinding
import com.repair.proj.login.adapter.LineTimeAdapter
import com.repair.proj.login.adapter.StepEnum
import com.repair.proj.login.contract.RegistThreeContract
import com.repair.proj.login.presenter.RegistThreePresenter
import com.repair.proj.nbase.NActivity
import com.repair.proj.utils.CameraUtil
import com.repair.proj.utils.CropUtil
import com.yalantis.ucrop.UCrop
import kotlinx.android.synthetic.main.activity_regist_three.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.io.File

/**
 * 说明：
 * Created by code_nil on 2017/10/27.
 */

class RegistThreeActivity : NActivity<RegistThreePresenter, ActivityRegistThreeBinding>(), RegistThreeContract.View, EasyPermissions.PermissionCallbacks {
    private var fromUri: Uri? = null
    private lateinit var rootPath: String
    override fun onInit() {
        super.onInit()
        rootPath = externalCacheDir.absolutePath
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

    override fun onListener() {
        super.onListener()
        art_next.setOnClickListener {
            startActivity<RegistForeActivity>()
        }

        art_idcard_ll_font.setOnClickListener { takeCamera(PHOTO_ID_FONT) }

        art_idcard_ll_back.setOnClickListener { takeCamera(PHOTO_ID_BACK) }

        art_idcard_ll_handheld.setOnClickListener { takeCamera(PHOTO_ID_HANDLE) }
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
                    fromUri?.let {
                        var toUri = Uri.fromFile(File(rootPath, "CROP${System.currentTimeMillis()}.jpg"))
                        CropUtil.cropRawPhoto(this, it, toUri, UCROP_ID_FONT)
                    }
                }
                PHOTO_ID_BACK -> {
                    fromUri?.let {
                        var toUri = Uri.fromFile(File(rootPath, "CROP${System.currentTimeMillis()}.jpg"))
                        CropUtil.cropRawPhoto(this, it, toUri, UCROP_ID_BACK)
                    }
                }
                PHOTO_ID_HANDLE -> {
                    fromUri?.let {
                        var toUri = Uri.fromFile(File(rootPath, "CROP${System.currentTimeMillis()}.jpg"))
                        CropUtil.cropRawPhoto(this, it, toUri, UCROP_ID_HANDLE)
                    }
                }
            //裁剪成功
                UCROP_ID_FONT -> {
                    data?.let {
                        Glide.with(this).load(UCrop.getOutput(data)).transition(DrawableTransitionOptions.withCrossFade()).into(art_idcard_font)
                    }
                }
                UCROP_ID_BACK -> {
                    data?.let {
                        Glide.with(this).load(UCrop.getOutput(data)).transition(DrawableTransitionOptions.withCrossFade()).into(art_idcard_back)
                    }
                }
                UCROP_ID_HANDLE -> {
                    data?.let {
                        Glide.with(this).load(UCrop.getOutput(data)).transition(DrawableTransitionOptions.withCrossFade()).into(art_idcard_handheld)
                    }
                }
            //裁剪失败
                UCrop.RESULT_ERROR -> {
                    if (data != null) toast(UCrop.getError(data)?.message ?: "")
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

    //相机拍照
    @AfterPermissionGranted(Common.PER_RC_CAMERA)
    private fun takeCamera(code:Int) {
        if (EasyPermissions.hasPermissions(this,
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            //生成rui
            var file = File(rootPath, "${System.currentTimeMillis()}.jpg")
            //7.0以上
            var fromUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                FileProvider.getUriForFile(this, getString(R.string.file_provider_authorities), file)
            } else {
                Uri.fromFile(file)
            }
            this.fromUri = fromUri
            if (!file.parentFile.exists()) {
                file.mkdirs()
            }
            CameraUtil.takeCamera(this, fromUri,code)
        } else {
            EasyPermissions.requestPermissions(this, "申请必要权限", Common.PER_RC_CAMERA,
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }
}
