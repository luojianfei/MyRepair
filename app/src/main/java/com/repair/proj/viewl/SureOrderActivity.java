package com.repair.proj.viewl;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.repair.proj.ContactInfo;
import com.repair.proj.R;
import com.repair.proj.base.BaseActivity;
import com.repair.proj.databinding.ActivitySureOrderBinding;
import com.repair.proj.utils.ActivityUtils;

/**
 * 确定订单
 * Created by HX·罗 on 2017/10/18.
 */

public class SureOrderActivity extends BaseActivity<ActivitySureOrderBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        color = Color.parseColor("#555555");
        super.onCreate(savedInstanceState);
    }

    @Override
    public int setContentView() {
        return R.layout.activity_sure_order;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        viewBinding.layoutTitle.setTitle("确认订单");
    }

    @Override
    public void initListener() {
        viewBinding.layoutTitle.setClickListener(this);
        viewBinding.setClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_telephone_book://电话本选择联系人
                ActivityUtils.startActivityForContacts(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1://获取联系人
                viewBinding.setContactInfo(getPhoneNumber(data));
                break;
        }
    }

    private ContactInfo getPhoneNumber(Intent intent) {
        Cursor cursor = null;
        Cursor phone = null;
        ContactInfo contactInfo = new ContactInfo();
        try {
            String[] projections = {ContactsContract.Contacts._ID, ContactsContract.Contacts.HAS_PHONE_NUMBER};
            cursor = getContentResolver().query(intent.getData(), projections, null, null, null);
            if ((cursor == null) || (!cursor.moveToFirst())) {
                return null;
            }
            int _id = cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID);
            String id = cursor.getString(_id);
            int has_phone_number = cursor.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER);
            int hasPhoneNumber = cursor.getInt(has_phone_number);
            if (hasPhoneNumber > 0) {
                phone = getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "="
                                + id, null, null);
                while (phone.moveToNext()) {
                    int numberIndex = phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    int nameIndex = phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                    String number = phone.getString(numberIndex);
                    String name = phone.getString(nameIndex);
                    contactInfo.setName(name);
                    contactInfo.setPhoneNo(number);
                }
            }
            return contactInfo;
        } catch (Exception e) {

        } finally {
            if (cursor != null) cursor.close();
            if (phone != null) phone.close();
        }
        return null;
    }
}
