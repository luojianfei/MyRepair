package com.repair.proj.order;

import com.repair.proj.R;
import com.repair.proj.databinding.ActivityMatchLocationBinding;
import com.repair.proj.nbase.NActivity;
import com.repair.proj.order.contract.MatchLocationContract;
import com.repair.proj.order.presenter.MatchLocationPresenter;

/**
 * 说明：
 * Created by code_nil on 2017/11/14.
 */

public class MatchLocationActivity extends NActivity<MatchLocationPresenter, ActivityMatchLocationBinding> implements MatchLocationContract.View {
    @Override
    protected int getContentId() {
        return R.layout.activity_match_location;
    }

}
