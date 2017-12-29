package com.repair.proj.user;

import android.content.Context;
import android.view.View;

import com.repair.proj.R;
import com.repair.proj.base.BaseActivity;
import com.repair.proj.databinding.ActivityMessageBinding;
import com.repair.proj.user.adapter.MessageListAdapter;
import com.repair.proj.user.contract.MessageContract;
import com.repair.proj.user.presenter.MessagePresenter;
import com.repair.proj.utils.Res;

import java.util.ArrayList;

/**
 * 创建人 LEO
 * 创建时间 2017/11/20 14:34
 */

public class MessageActivity extends BaseActivity<MessagePresenter,ActivityMessageBinding> implements MessageContract.View {
    private ArrayList<Object> messageInfos ;
    private MessageListAdapter adapter;

    @Override
    public int setContentView() {
        return R.layout.activity_message ;
    }

    @Override
    public void initView() {
        viewBinding.layoutTitle.setRightDrawable(Res.getDrawableRes(R.drawable.more,context));
    }

    @Override
    public void initData() {
        messageInfos = new ArrayList<>() ;
        viewBinding.layoutTitle.setTitle("收件信箱");
        adapter = new MessageListAdapter(context,messageInfos);
        viewBinding.crvList.setAdapter(adapter);
    }

    @Override
    public void initListener() {
        viewBinding.layoutTitle.setClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_back:
                finish();
                break ;
            case R.id.iv_right:
                break;
            default:
                break ;
        }
    }

    @Override
    public Context context() {
        return context;
    }

    @Override
    public void showMsg(String msg) {
        showShortToast(msg);
    }
}
