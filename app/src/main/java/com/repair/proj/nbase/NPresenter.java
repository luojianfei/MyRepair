package com.repair.proj.nbase;

/**
 * 说明：
 * Created by code_nil on 2017/10/27.
 */

public class NPresenter<T extends NContract.View, E extends NContract.Model> {
    protected T view;
    protected E model;

    public void init(Object view, Object model) {
        this.view = (T) view;
        this.model = (E) model;
    }

    /**
     * 当onCreate或onCreateView方法执行完毕将会调用
     */
    public void start() {}

    /**
     * 当onDestroy或onDestroyView方法执行完毕将会调用
     */
    public void end() {
        view = null;
        model = null;
    }
}
