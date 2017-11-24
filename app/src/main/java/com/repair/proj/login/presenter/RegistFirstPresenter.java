package com.repair.proj.login.presenter;

import com.repair.proj.login.contract.RegistFirstContract;
import com.repair.proj.login.model.RegistFirstModel;
import com.repair.proj.nbase.NContract;
import com.repair.proj.nbase.NPresenter;
import com.repair.proj.utils.RxCountdown;

import rx.Subscriber;
import rx.functions.Action0;

/**
 * Created by HX·罗 on 2017/10/23.
 */

public class RegistFirstPresenter extends NPresenter<RegistFirstContract.View,RegistFirstModel> implements RegistFirstContract.Presenter {
    @Override
    public void requestValidateCode() {
        setValidateCodeShow(60);
    }

    private void setValidateCodeShow(int time){
        RxCountdown.countdown(time)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        view.startTimeDown();
                    }
                })
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        view.endTimeDown();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        view.setTimeDown(integer);
                    }
                });
    }

    @Override
    public void next() {

    }
}
