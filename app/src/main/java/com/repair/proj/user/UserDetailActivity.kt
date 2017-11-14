package com.repair.proj.user

import android.Manifest
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.support.v4.content.FileProvider
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.repair.proj.R
import com.repair.proj.base.Common
import com.repair.proj.databinding.ActivityUserDetailBinding
import com.repair.proj.user.adapter.RepairTypeAdapter
import com.repair.proj.user.contract.UserDetailContract
import com.repair.proj.user.presenter.UserDetailPresenter
import com.repair.proj.nbase.NActivity
import com.repair.proj.utils.CameraUtil
import com.repair.proj.utils.CropUtil
import com.repair.proj.order.SureOrderActivity
import com.yalantis.ucrop.UCrop
import kotlinx.android.synthetic.main.activity_user_detail.*
import kotlinx.android.synthetic.main.activity_user_detail_order.*
import kotlinx.android.synthetic.main.activity_user_drawer.*
import org.jetbrains.anko.*
import org.jetbrains.anko.collections.forEachWithIndex
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.io.File

/**
 * 说明：databinding只用于xml数据的绑定
 * Created by code_nil on 2017/10/23.
 */

class UserDetailActivity : NActivity<UserDetailPresenter,
        ActivityUserDetailBinding>(), UserDetailContract.View, AnkoLogger, EasyPermissions.PermissionCallbacks {
    private val tabItems = arrayOf("水电", "漆工", "木工", "泥工", "疏通")//tablayout项目
    private val layoutList = arrayOf(R.drawable.md_sd, R.drawable.md_qg, R.drawable.md_mg, R.drawable.md_ng, R.drawable.md_st)//viewpager图标
    private var pagerViewList = arrayListOf<View>()
    private lateinit var rootPath: String
    private lateinit var pictureViews: List<ImageView>
    private var upPicCount = 0
    private var fromUri: Uri? = null
    override fun onInit() {
        super.onInit()
        rootPath = externalCacheDir.absolutePath
        pictureViews = arrayListOf(md_pic_1, md_pic_2, md_pic_3)
        //初始化项目选择
        presenter.initCustomOptionPicker(md_pj_select_detail_picker, this)
        //默认不显示项目选择布局
        binding.pickerShow = false
        //默认数量为1
        //初始化加载项 tabTypes，layoutList需要从网络上获取
        md_tb_name.typeface = Typeface.DEFAULT_BOLD
        //初始化viewpager
        pagerViewList.clear()
        tabItems.forEachWithIndex { id, data ->
            //            md_tab.addTab(md_tab.newTab().setText(data).setTag(id))
            var view = layoutInflater.inflate(R.layout.activity_main_detail_image, null)
            view.tag = data
            view.find<ImageView>(R.id.amdi_image).setImageResource(layoutList[id])
            pagerViewList.add(view)
        }

        var adapter = RepairTypeAdapter(pagerViewList)
        md_pj_vp.adapter = adapter
        md_tab.setupWithViewPager(binding.mdPjVp)
        md_tab.setTabsFromPagerAdapter(adapter)
        Glide.with(this).load(R.drawable.header).apply((RequestOptions.bitmapTransform(CircleCropTransformation()))).into(md_side_header)
    }

    override fun onListener() {
        super.onListener()
        //viewpager监听事件
        md_pj_vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                //隐藏按钮
                when {
                    md_pj_vp.currentItem == 0 -> binding.arrowLShow = false
                    md_pj_vp.currentItem == tabItems.size - 1 -> binding.arrowRShow = false
                    else -> {
                        binding.arrowLShow = true
                        binding.arrowRShow = true
                    }
                }
            }

            override fun onPageSelected(position: Int) {}
        })
        //左箭头点击事件
        md_pj_arrow_l.setOnClickListener {
            binding.mdPjVp.setCurrentItem(binding.mdPjVp.currentItem - 1, true)
        }
        //右箭头点击事件
        md_pj_arrow_r.setOnClickListener {
            binding.mdPjVp.setCurrentItem(binding.mdPjVp.currentItem + 1, true)
        }
        //定位控件点击事件
        md_location_content.setOnClickListener {
            startActivityForResult<UserLocationActivity>(Common.LOCATION_REQUEST_CODE)
        }

        md_pj_select_ll.setOnClickListener {
            binding.pickerShow = !(binding.pickerShow ?: false)
            presenter.showOrDismissDetailPicker(binding.pickerShow ?: false)
        }

        md_num_reduce.setOnClickListener {
            var num = binding.num ?: 1
            num--
            if (num < 1) num = 1
            binding.num = num
        }

        md_num_add.setOnClickListener {
            var num = binding.num ?: 0
            num++
            if (num < 1) num = 1
            binding.num = num
        }

        md_scrollView.setOnTouchListener { _, _ -> true }

        md_pic.setOnClickListener {
            takeCamera()
        }

        md_tb_contact.setOnClickListener {
            md_drawer.openDrawer(md_drawer_left)
        }

        //维修接口
        md_repair_now.setOnClickListener { startActivity<SureOrderActivity>() }
        md_repair_later.setOnClickListener { startActivity<SureOrderActivity>() }
    }

    override fun getContentId(): Int {
        return R.layout.activity_user_detail
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
            //定位返回
                Common.LOCATION_REQUEST_CODE -> {
                    if (data != null)
                        binding.address = data.getStringExtra("address") ?: ""
                }
            //拍照返回
                Common.PHOTO_TAKE -> {
                    fromUri?.let {
                        var toUri = Uri.fromFile(File(rootPath, "CROP${System.currentTimeMillis()}.jpg"))
                        CropUtil.cropRawPhoto(this, it, toUri, Common.UCROP_TAKE)
                    }
                }
            //裁剪成功
                Common.UCROP_TAKE -> {
                    // 使用Glide显示图片
                    if (data != null && upPicCount < 3) {
                        var view = pictureViews[upPicCount++]
                        view.visibility = View.VISIBLE
                        Glide.with(this).load(UCrop.getOutput(data)).transition(DrawableTransitionOptions().crossFade(500)).into(view)
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
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }

    //相机拍照
    @AfterPermissionGranted(Common.PER_RC_CAMERA)
    private fun takeCamera() {
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
            CameraUtil.takeCamera(this, fromUri, Common.PHOTO_TAKE)
        } else {
            EasyPermissions.requestPermissions(this, "申请必要权限", Common.PER_RC_CAMERA,
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }

}
