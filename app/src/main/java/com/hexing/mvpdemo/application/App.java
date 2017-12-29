package com.hexing.mvpdemo.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @author by HEC271
 *         on 2017/12/28.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
