package com.base.web.okhttp;

import android.os.Handler;
import android.os.Looper;

import java.util.Map;

/**
 * @author long
 *         统一封装web接口中间层
 **/
public abstract class HttpBusinessCallback implements HttpCallback {

    @Override
    public void onFailure(Map<String, ?> errorMap) {
    }

    @Override
    public void onFailure(final Exception ex) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                onFailed(ex);
            }
        });
    }

    @Override
    public void onSucceed(final String response) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                onSuccess(response);
            }
        });
    }

    public abstract void onSuccess(String response);
    public abstract void onFailed(Exception ex);
}