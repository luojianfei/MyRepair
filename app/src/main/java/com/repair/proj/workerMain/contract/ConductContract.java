package com.repair.proj.workerMain.contract;

import com.repair.proj.nbase.NContract;

/**
 * Created by HX·罗 on 2017/11/1.
 */

public interface ConductContract {
    interface View extends NContract.View{
    }

    interface Presenter extends NContract.Presenter{
    }

    interface Model extends NContract.Model{
    }
}
