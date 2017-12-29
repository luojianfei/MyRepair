package com.repair.proj.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.WindowManager;

import com.repair.proj.R;
import com.repair.proj.databinding.DialogShowTipBinding;
import com.repair.proj.databinding.LayoutDialogBinding;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Description:显示指定组件的对话框,并跳转至指定的Activity
 */
public class DialogUtils {


    public interface EditContentDialogListener {
        public void positive(String content);//确定

        public void nagtive();//取消
    }


    public interface DialogComfirmListener {
        void comfirmListener(String dialog);
    }

    public interface DialogDismissListener {
        public void dismiss(AlertDialog dialog);
    }

    private static AlertDialog builder;

    /**
     * 提问框的 Listener
     *
     * @author Lei
     */
    // 因为本类不是activity所以通过继承接口的方法获取到点击的事件
    public interface OnClickYesListener {
        abstract void onClickYes();
    }

    /**
     * 提问框的 Listener
     */
    public interface OnClickNoListener {
        abstract void onClickNo();
    }

    /**
     * 处理字符的方法
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 环形
     *
     * @param dialog
     * @param context
     */
    public void showProgressDialog(ProgressDialog dialog, Context context) {

//        dialog = new ProgressDialog(context);

        //设置进度条风格，风格为圆形，旋转的
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //设置ProgressDialog 标题
        dialog.setMessage("加载中...");
        //置ProgressDialog 的进度条是否不明确
        dialog.setIndeterminate(false);
        //设置ProgressDialog 是否可以按退回按键取消
        dialog.setCancelable(false);

        //显示
        dialog.show();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        WindowManager wm = (WindowManager) context.getSystemService(WINDOW_SERVICE);
        params.width = wm.getDefaultDisplay().getWidth() * 2 / 3;
//        dialog.getWindow().setBackgroundDrawableResource(android.R.color.background_light);
        dialog.getWindow().setAttributes(params);
    }

    public static AlertDialog showDialog(Context context, String tip, final DialogDismissListener dismissListener) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        View view = View.inflate(context, R.layout.layout_dialog, null);
        LayoutDialogBinding viewBinding = DataBindingUtil.bind(view);
        viewBinding.setTip(tip);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog1) {
                if (dismissListener != null) {
                    dismissListener.dismiss(dialog);
                }
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));
        dialog.show();
        dialog.setContentView(view);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = ScreenUtils.getScreenWidth(context) / 2;
        dialog.getWindow().setAttributes(params);
        return dialog;
    }


    public static void showDialog4View(Context context, int res, int positiveId, int nagtiveId, final EditContentDialogListener listener){
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes() ;
        dialog.getWindow().setAttributes(params);
        dialog.setCanceledOnTouchOutside(false);
        View view = View.inflate(context,res,null) ;
        if(positiveId != -1){
            View positive = view.findViewById(positiveId) ;
            positive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.positive("");
                    dialog.dismiss();
                }
            });
        }
        if(nagtiveId != -1){
            View positive = view.findViewById(positiveId) ;
            positive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.nagtive();
                    dialog.dismiss();
                }
            });
        }

        dialog.show();
        dialog.setContentView(view);
    }

    /**
     * 自定义提示信息
     * @param context
     * @param content
     */
    public static void showTipDialog(Context context, String content){
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.getWindow().setDimAmount(0f);
        View view = View.inflate(context,R.layout.dialog_show_tip,null) ;
        DialogShowTipBinding binding = DataBindingUtil.bind(view) ;
        binding.setContent(content);
        dialog.show();
        dialog.setContentView(view);
        view.postDelayed(new Runnable() {//2秒后自动消失
            @Override
            public void run() {
                dialog.dismiss();
            }
        },2000) ;
    }

}