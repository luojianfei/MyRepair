package com.repair.proj.userLogin.presenter;

import com.repair.proj.login.contract.RegistFirstContract;
import com.repair.proj.login.model.RegistFirstModel;
import com.repair.proj.nbase.NContract;
import com.repair.proj.nbase.NPresenter;
import com.repair.proj.userLogin.contract.UserRegistFirstContract;

/**
 * Created by HX·罗 on 2017/10/23.
 */

public class UserRegistFirstPresenter extends NPresenter<NContract.View,RegistFirstModel> implements UserRegistFirstContract.Presenter {
    @Override
    public void requestValidateCode() {

    }

    @Override
    public void next() {

    }
}
