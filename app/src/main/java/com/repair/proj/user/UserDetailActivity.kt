package com.repair.proj.user

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.repair.proj.R
import com.repair.proj.base.Common
import com.repair.proj.databinding.ActivityUserDetailBinding
import com.repair.proj.nbase.NActivity
import com.repair.proj.order.OrderRecordActivity
import com.repair.proj.order.SureOrderActivity
import com.repair.proj.user.adapter.RepairTypeAdapter
import com.repair.proj.user.adapter.SelectPictureAdapter
import com.repair.proj.user.contract.UserDetailContract
import com.repair.proj.user.presenter.UserDetailPresenter
import com.repair.proj.utils.PhotoPickerUtil
import kotlinx.android.synthetic.main.activity_user_detail.*
import kotlinx.android.synthetic.main.activity_user_drawer.*
import me.iwf.photopicker.PhotoPicker
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.collections.forEachWithIndex
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

/**
 * 说明：databinding只用于xml数据的绑定
 * Created by code_nil on 2017/10/23.
 */

class UserDetailActivity : NActivity<UserDetailPresenter,
        ActivityUserDetailBinding>(), UserDetailContract.View, AnkoLogger, View.OnClickListener, EasyPermissions.PermissionCallbacks {

    private val tabItems = arrayOf("水电", "漆工", "木工", "泥工", "疏通")//tablayout项目
    private val layoutList = arrayOf(R.drawable.md_sd, R.drawable.md_qg, R.drawable.md_mg, R.drawable.md_ng, R.drawable.md_st)//viewpager图标
    private var pagerViewList = arrayListOf<View>()
    private lateinit var rootPath: String
    private var fromUri: Uri? = null
    private lateinit var sAdapter: SelectPictureAdapter
    private lateinit var ppu: PhotoPickerUtil
    override fun onInit() {
        super.onInit()
        ppu = PhotoPickerUtil(this)
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
        //初始化选择项目recylerview
        var adapter = RepairTypeAdapter(pagerViewList)
        md_pj_vp.adapter = adapter
        md_tab.setupWithViewPager(binding.mdPjVp)
        md_tab.setTabsFromPagerAdapter(adapter)
        md_side_header.tag = null
        Glide.with(this).load(R.drawable.header).apply((RequestOptions.bitmapTransform(CircleCropTransformation()))).into(md_side_header)
        //初始化显示图片recyclerview
        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        sAdapter = SelectPictureAdapter(ppu.selectPhotos, this)
        md_rv_select_pictures.layoutManager = layoutManager
        md_rv_select_pictures.adapter = sAdapter
        //初始化最开始时实现左右箭头的显示和关闭
        binding.arrowLShow = true
        binding.arrowRShow = true
        var item = md_pj_vp.currentItem
        if (item == 0) binding.arrowLShow = false
        if (item == tabItems.size - 1) binding.arrowRShow = false
    }

    override fun onListener() {
        super.onListener()
        //本页面实现了点击事件接口，则不需要再为binding设置监听事件
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
        //处理事件冲突
        md_scrollview.setOnTouchListener { v, event ->
            md_pj_select_detail_picker.parent.requestDisallowInterceptTouchEvent(false)
            false
        }
        binding.layoutDrawer!!.clickListener = View.OnClickListener { v ->
            when(v!!.id){
                R.id.md_side_oder_record ->{
                    startActivity<OrderRecordActivity>()
                }
                R.id.md_side_master ->{//我的师傅
                    startActivity<MyWorkerActivity>()
                }
                R.id.md_side_ticket ->{//修修券
                    startActivity<MyCouponActivity>()
                }
                R.id.md_side_mail ->{//收件箱
                    startActivity<MessageActivity>()
                }
                R.id.md_side_service ->{//客服中心
                    startActivity<ServiceCenterActivity>()
                }
                R.id.md_side_rewards ->{//邀请有奖
                    startActivity<InviteRewardActivity>()
                }
                R.id.md_side_setting ->{//更多设置
                    startActivity<MoreSettingActivity>()
                }
            }
        }
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

                Common.PHOTO_PREVIEW -> {
                    if (data != null) {
                        var checkPhotos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS)
                        ppu.selectPhotos.clear()
                        ppu.selectPhotos.addAll(checkPhotos)
                        checkAddBtState()
                        sAdapter.notifyDataSetChanged()
                    }
                }

                Common.PHOTO_TAKE -> {
                    var path = ppu.currentPhotoPath()
                    if (!TextUtils.isEmpty(path)) {
                        ppu.selectPhotos.add(path)
                        checkAddBtState()
                        sAdapter.notifyDataSetChanged()
                    }
                }

                Common.PHOTO_ALBUM->{
                    if (data != null) {
                        var checkPhotos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS)
                        ppu.selectPhotos.addAll(checkPhotos)
                        checkAddBtState()
                        sAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }


    private fun checkAddBtState() {
        if (ppu.selectPhotos.size == 3) {
            md_pic_add.visibility = View.GONE
        } else {
            md_pic_add.visibility = View.VISIBLE
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

    //具体点击事件
    override fun onClick(v: View) {
        when (v.id) {
        //左箭头
            R.id.md_pj_arrow_l -> {
                md_pj_vp.setCurrentItem(binding.mdPjVp.currentItem - 1, true)
            }
        //右箭头
            R.id.md_pj_arrow_r -> {
                md_pj_vp.setCurrentItem(binding.mdPjVp.currentItem + 1, true)
            }
        //定位控件
            R.id.md_location_content -> {
                startActivityForResult<UserLocationActivity>(Common.LOCATION_REQUEST_CODE)
            }
        //隐藏项目
            R.id.md_pj_select_ll -> {
                binding.pickerShow = !(binding.pickerShow ?: false)
                presenter.showOrDismissDetailPicker(binding.pickerShow ?: false)
            }
        //增加按钮
            R.id.md_num_add -> {
                var num = binding.num ?: 0
                num++
                if (num < 1) num = 1
                binding.num = num
            }
        //增加按钮
            R.id.md_num_reduce -> {
                var num = binding.num ?: 1
                num--
                if (num < 1) num = 1
                binding.num = num
            }
        //添加维修图片
            R.id.md_pic_add -> {
                var checkCount = 3 - ppu.selectPhotos.size
                ppu.showTakePhotoDialog(Common.PHOTO_TAKE, Common.PHOTO_ALBUM, checkCount)
            }
        //侧滑栏
            R.id.md_tb_contact -> {
                md_drawer.openDrawer(Gravity.RIGHT)
            }
        //马上维修
            R.id.md_repair_now -> {
                startActivity<SureOrderActivity>()
            }
        //预约维修
            R.id.md_repair_later -> {
                startActivity<SureOrderActivity>()
            }
        }
    }


}
