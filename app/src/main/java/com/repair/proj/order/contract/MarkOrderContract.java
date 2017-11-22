package com.repair.proj.order.contract;

import android.nfc.tech.NfcV;

import com.repair.proj.nbase.NContract;

/**
 * 说明：
 * Created by code_nil on 2017/11/22.
 */

public interface MarkOrderContract {
    interface View extends NContract.View {
        void method();
    }

    interface Presenter extends NContract.Presenter {
        void method();
    }

    interface Model extends NContract.Model {
        void method();
    }
}
