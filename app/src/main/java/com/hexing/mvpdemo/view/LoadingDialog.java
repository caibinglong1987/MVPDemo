package com.hexing.mvpdemo.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.Window;
import android.widget.TextView;

import com.hexing.mvpdemo.R;


/**
 * @author long
 * 加载进度条
 */
@SuppressLint("NewApi")
public class LoadingDialog {
    public static AlertDialog loadingDialog = null;
    private static ILoadingCall call;

    public static void showSysLoadingDialog(Context con, String title) {
        showSysLoadingDialog(con, title, true);
    }

    public static void setCanceListener(ILoadingCall call) {
        LoadingDialog.call = call;
    }

    /**
     * 模拟系统加载框
     *
     * @param con
     * @param title
     */
    public static void showSysLoadingDialog(Context con, String title, boolean bool) {
        //DebugLogs.e("============response==========系统load=="+String.valueOf(con));
        try {
            if (loadingDialog != null) {
                Window window = loadingDialog.getWindow();
                TextView dTitle = (TextView) window.findViewById(R.id.dialog_loading_title);
                if (title != null) {
                    dTitle.setText(title);
                }
                loadingDialog.show();
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(con);
            builder.setCancelable(bool);
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    //PrintUtil.log("Loading", "取消加载事件");
                    if (LoadingDialog.call != null) {
                        LoadingDialog.call.cancel();
                    }
                    loadingDialog.dismiss();
                    loadingDialog = null;
                }
            });
            loadingDialog = builder.create();
            loadingDialog.setCanceledOnTouchOutside(bool);
            loadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// set background was transparent
            loadingDialog.getWindow().setDimAmount(0.5f);// 设置弹框遮盖层隐藏
            loadingDialog.show();
            Window window = loadingDialog.getWindow();
            window.setGravity(Gravity.CENTER);// 居顶显示
            window.setContentView(R.layout.dialog_progress);
            TextView dTitle = (TextView) window.findViewById(R.id.dialog_loading_title);
            if (title != null) {
                dTitle.setText(title);
            }
        } catch (Exception ex) {
        }
    }

    public static void cancelLoadingDialog() {
        try {
            if (loadingDialog != null) {
                loadingDialog.dismiss();
                loadingDialog = null;
            }
        } catch (Exception e) {
        }
    }

    public static void setTitle(String title) {
        if (loadingDialog != null) {
            Window window = loadingDialog.getWindow();
            if (window != null) {
                TextView dTitle = (TextView) window.findViewById(R.id.dialog_loading_title);
                if (title != null) {
                    dTitle.setText(title);
                }
                loadingDialog.show();
            }
        }
    }

    public interface ILoadingCall {
        void cancel();
    }
}
