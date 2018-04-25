package com.hexing.mvpdemo.application;


import com.facebook.drawee.backends.pipeline.Fresco;
import com.hexing.libhexbase.application.HexApplication;

/**
 * @author by HEC271
 *         on 2017/12/28.
 */

public class App extends HexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
