package com.repair.proj.nbase;

import android.app.AlertDialog;
import android.content.Context;

import com.repair.proj.utils.DialogUtils;
import com.repair.proj.utils.TextUtil;

import org.jetbrains.annotations.NotNull;

import java.util.TreeMap;

/**
 * 说明：
 * Created by code_nil on 2017/10/27.
 */

public class NPresenter<T extends NContract.View, E extends NContract.Model> {
    protected T view;
    protected E model;
    private AlertDialog dialog ;
    public Context context ;

    public void init(Object view, Object model) {
        this.view = (T) view;
        this.model = (E) model;
        context = ((T) view).context() ;
    }

    /**
     * 打开请求dialog
     */
    public void showDialog(String msg){
        try{
            if (dialog == null) {
                if(TextUtil.isEmpty(msg))
                    msg = "请求数据中" ;
                dialog = DialogUtils.showDialog(view.context(), msg, null);
            } else {
                dialog.show();
            }
        }catch(Exception e){
            e.printStackTrace() ;
        }
    }

    /**
     * 组装请求数据
     * @param keys
     * @param values
     * @return
     */
    public TreeMap<String,String> createTreeMap(@NotNull String[] keys,@NotNull String[] values){
        TreeMap<String,String> params = new TreeMap<>() ;
        for(int i = 0; i < keys.length ; i++){
            params.put(keys[i],values[i]) ;
        }
        return params ;
    }

    /**
     * 关闭dialog
     */
    public void closeDialog(){
        if(dialog != null){
            dialog.dismiss();
        }
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
